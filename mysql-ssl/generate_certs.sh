
# Create Certs folder if not present
mkdir -p certs

find ./certs -name '*.pem' -delete

OPENSSL_SUBJ="/C=NL/ST=Noord Holland/L=Amsterdam"
OPENSSL_CA="${OPENSSL_SUBJ}/CN=dummy-CA"
OPENSSL_SERVER="${OPENSSL_SUBJ}/CN=dummy-server"
OPENSSL_CLIENT="${OPENSSL_SUBJ}/CN=dummy-client"

# Create Fake CA Certificate

docker run --rm -v $PWD/certs:/certs -it nginx \
    openssl genrsa 2048 > certs/root-ca-key.pem

docker run --rm -v $PWD/certs:/certs -it nginx \
    openssl req -new -x509 -nodes -days 3600 \
        -subj "${OPENSSL_CA}" \
        -key /certs/root-ca-key.pem -out /certs/root-ca.pem

# Create Fake Server Certificate

docker run --rm -v $PWD/certs:/certs -it nginx \
    openssl req -newkey rsa:2048 -days 3600 -nodes \
        -subj "${OPENSSL_SERVER}" \
        -keyout /certs/server-key.pem -out /certs/server-req.pem

docker run --rm -v $PWD/certs:/certs -it nginx \
    openssl rsa -in /certs/server-key.pem -out /certs/server-key.pem

docker run --rm -v $PWD/certs:/certs -it nginx \
    openssl x509 -req -in /certs/server-req.pem -days 3600 \
        -CA /certs/root-ca.pem -CAkey /certs/root-ca-key.pem \
        -set_serial 01 -out /certs/server-cert.pem

# Create Fake Client Certificate

docker run --rm -v $PWD/certs:/certs -it nginx \
    openssl req -newkey rsa:2048 -days 3600 -nodes \
        -subj "${OPENSSL_CLIENT}" \
        -keyout /certs/client-key.pem -out /certs/client-req.pem

docker run --rm -v $PWD/certs:/certs -it nginx \
    openssl rsa -in /certs/client-key.pem -out /certs/client-key.pem

docker run --rm -v $PWD/certs:/certs -it nginx \
    openssl x509 -req -in /certs/client-req.pem -days 3600 \
        -CA /certs/root-ca.pem -CAkey /certs/root-ca-key.pem \
        -set_serial 01 -out /certs/client-cert.pem

# Verify both Server and Client Certificate

docker run --rm -v $PWD/certs:/certs -it nginx \
    openssl verify -CAfile /certs/root-ca.pem /certs/server-cert.pem

docker run --rm -v $PWD/certs:/certs -it nginx \
    openssl verify -CAfile /certs/root-ca.pem /certs/client-cert.pem


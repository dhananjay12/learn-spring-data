
# Create java folder if not present
mkdir -p java

find ./java -name '*.jks' -delete

OPENSSL_SUBJ="/C=NL/ST=Noord Holland/L=Amsterdam"
OPENSSL_CA="${OPENSSL_SUBJ}/CN=dummy-CA"
OPENSSL_SERVER="${OPENSSL_SUBJ}/CN=dummy-server"
OPENSSL_CLIENT="${OPENSSL_SUBJ}/CN=dummy-client"

# Create pks for java app

docker run --rm -v $PWD/certs:/certs -v $PWD/java:/java -it openjdk:11-jdk-slim \
    openssl pkcs12 -export -in /certs/root-ca.pem -inkey /certs/root-ca-key.pem \
    -passout pass:mypass -out /java/client-keystore.p12





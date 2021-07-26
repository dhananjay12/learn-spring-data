## How to Generate Certificate

Generate CA Certificate

```
openssl genrsa 2048 > ca-key.pem
openssl req -new -x509 -nodes -days 3600 -key ca-key.pem -out ca.pem
```

Create Server Certifcate and Sign with CA

```
openssl req -newkey rsa:2048 -days 3600 -nodes -keyout server-key.pem -out server-req.pem

openssl rsa -in server-key.pem -out server-key.pem

openssl x509 -req -in server-req.pem -days 3600 -CA ca.pem -CAkey ca-key.pem -set_serial 01 -out server-cert.pem
```

Create Client certificate, Sign with CA

```
openssl req -newkey rsa:2048 -days 3600 -nodes -keyout client-key.pem -out client-req.pem

openssl rsa -in client-key.pem -out client-key.pem

openssl x509 -req -in client-req.pem -days 3600 -CA ca.pem -CAkey ca-key.pem -set_serial 01 -out client-cert.pem
```

Verify the Certificate

```
openssl verify -CAfile ca.pem server-cert.pem client-cert.pem
```

Note - The Common Name value used for the server and client certificates/keys must each differ from the Common Name 
value used for the CA certificate. Otherwise, the certificate and key files will not work for 
servers compiled using OpenSSL. Use following names for example:
* CA Cert:  ssl-ca-cert
* Server Cert: ssl-ca-server-cert
* Client Cert:  ssl-ca-client-cert


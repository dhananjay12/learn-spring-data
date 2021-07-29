## How to Generate Certificate

Generate Certificate

```
sh generate_certs.sh
```

Note - The Common Name value used for the server and client certificates/keys must each differ from the Common Name 
value used for the CA certificate. Otherwise, the certificate and key files will not work for 
servers compiled using OpenSSL. Use following names for example:
* CA Cert:  ssl-ca-cert
* Server Cert: ssl-ca-server-cert
* Client Cert:  ssl-ca-client-cert

Go inside Container

```
docker exec -it mysql bash
mysql  -u root -p --ssl-ca=/etc/certs/ca.pem --ssl-cert=/etc/certs/client-cert.pem --ssl-key=/etc/certs/client-key.pem
```


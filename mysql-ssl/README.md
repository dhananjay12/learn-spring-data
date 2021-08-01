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

### Check setup

Go Inside Mysql server container and run `mysql_ssl_rsa_setup`:

```
docker exec -it mysql bash
mysql_ssl_rsa_setup --datadir=/etc/certs --verbose
```
https://dev.mysql.com/doc/refman/5.7/en/mysql-ssl-rsa-setup.html

## Create User Server Container

```
docker exec -it mysql bash
mysql  -u root -p --ssl-ca=/etc/certs/ca.pem --ssl-cert=/etc/certs/client-cert.pem --ssl-key=/etc/certs/client-key.pem
status
show global variables like '%ssl%';
```

### Create SSL User

By default, a `ssluser` is created what require SSL to connect via init.sql which runs following:

```
CREATE USER 'ssluser'@'%' REQUIRE SSL;
GRANT ALL PRIVILEGES ON `testdb`.* TO 'ssluser'@'%';
```

## Connect via Client Container

```
docker exec -it mysql-client bash
mysql -h mysql -P 3306 -u ssluser -p
status
show global variables like '%ssl%';
```

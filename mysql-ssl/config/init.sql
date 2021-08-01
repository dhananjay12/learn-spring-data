CREATE USER 'ssluser'@'%' REQUIRE SSL;
GRANT ALL PRIVILEGES ON `testdb`.* TO 'ssluser'@'%';
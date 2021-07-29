CREATE USER IF NOT EXISTS 'spring'@'%';
ALTER USER 'spring'@'%' REQUIRE SSL;
GRANT ALL PRIVILEGES ON `testdb`.* TO 'spring'@'%';
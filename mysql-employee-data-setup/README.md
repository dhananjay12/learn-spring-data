## Mysql Database with Employee Data

Spin up mysql database:

```
docker-compose up -d
```


Execute the script to create the database:

```bash
docker exec -it mysql-container /bin/bash
```

The test data used in this project is from [datacharmer/test_db](https://github.com/datacharmer/test_db). Documented [here](https://dev.mysql.com/doc/employee/en/).

Run the script to create the database:

```bash
cd /initdb/test_db/
mysql -u root -proot < employees.sql
```
Above script will create a database called `employees` and a many tables inside it with the dump data.

### Test the data (Optional)

To test the data, run the following command:

```bash
time mysql -proot -t < test_employees_md5.sql
```
or
    
```bash
time mysql -proot -t < test_employees_sha.sql
```

### Stop the container

To stop the container:

```bash
docker-compose down
```
## Mysql Database with Employee Data

Spin up mysql database:

```
docker-compose up -d
```


Execute the script to create the database:

```bash
docker exec -it mysql-container /bin/bash
```

Run the script:

```bash
mysql -u root -proot < employees.sql
```
Above script will create a database called `employees` and a many tables inside it with the dump data.

### Test the data (Optional)

To test the data, run the following command:

```bash
sh sql_test.sh 'mysql -proot'
```

NOTE: - The script checks the expected count in each table. See the first result as `OK`.

To check individual table count, run the following command:

```bash
mysql -u root -proot -e "SELECT COUNT(*) FROM employees.employees;"
```

### Stop the container

To stop the container:

```bash
docker-compose down
```
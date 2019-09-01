docker run -p 3306:3306 --name mysql_5 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5
docker exec -it mysql_5 bash
mysql -u root -p
create database test;
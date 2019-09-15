# MySQL
docker run -p 3306:3306 --name mysql_5 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5
docker exec -it mysql_5 bash
mysql -u root -p
create database test;

# Oracle DB
## build docker image
git clone https://github.com/oracle/docker-images.git
copy the installation binaries of Oracle Database and put them into the dockerfiles/<version> folder
### use git bash // https://github.com/oracle/docker-images/tree/master/OracleDatabase/SingleInstance
cd docker-images/OracleDatabase/SingleInstance/dockerfiles
./buildDockerImage.sh -v 12.2.0.1 -e -i

## create container
docker run -p 1521:1521 -p 5050:5050 -p 8088:8080 --name oracleDB12.2.0.1ee -e ORACLE_PWD=123456 oracle/database:12.2.0.1-ee

## add user
ALTER SESSION SET CONTAINER=ORCLPDB1;
CREATE USER test_user IDENTIFIED BY 123456;
GRANT CONNECT,RESOURCE TO test_user;
GRANT UNLIMITED TABLESPACE TO test_user;

## add ojdbc8.jar to local maven repository
mvn install:install-file -Dfile=ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar


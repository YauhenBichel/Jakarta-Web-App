### Wildfly
v: 26.0.1.Final
https://www.wildfly.org/downloads/

### JBoss Tools Plugin
JBoss Tools provides, among others, integration between Eclipse and WildFly.

>mvn install
>mvn clean

### URI
http://localhost:8080/holidaysystem/api/hello

#### add a user
add any changes for user (wildfly/bin)
>sh ./add-user.sh
user:comp1610y
pass:password1
server in browser
>http://127.0.0.1:9990/management
http://127.0.0.1:9990/console/index.html	


### database

postgresql 42.3.3
https://jdbc.postgresql.org/download.html

Host: ec2-52-214-125-106.eu-west-1.compute.amazonaws.com
Database: dfejis77nibjdr
User: qexdvxvgdqtdgh
Port: 5432
Password: bf7f8df51b60c17d1fcc333c3f7b63e57df7300022deb67d60eb6cee2bd4e5dd
URI: postgres://qexdvxvgdqtdgh:bf7f8df51b60c17d1fcc333c3f7b63e57df7300022deb67d60eb6cee2bd4e5dd@ec2-52-214-125-106.eu-west-1.compute.amazonaws.com:5432/dfejis77nibjdr
Heroku CLI: heroku pg:psql postgresql-infinite-73558 --app holiday-system
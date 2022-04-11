### Wildfly
v: 26.0.1.Final
https://www.wildfly.org/downloads/

- Copy standalone.xml into Wildfly/standalone/configuration folder

- Open http://127.0.0.1:9990/console/index.html	

- login using 
user: comp1610y
pass: password1




### JBoss Tools Plugin
JBoss Tools provides, among others, integration between Eclipse and WildFly.

>mvn install
>mvn clean

### URI
http://localhost:8080/holidaysystem/api/hello

context path: /holidaysystem
servlet path: /api
path info: /hello

#### add a user
add any changes for user (wildfly/bin)
>sh ./add-user.sh
user: comp1610y
pass: password1
server in browser
http://127.0.0.1:9990/console/index.html	

### Logging
Level is DEBUG
<file relative-to="jboss.server.log.dir" path="holidaysystem.log"/>
                <suffix value=".yyyy-MM-dd"/>
                <append value="true"/>
org.jboss.logging.Logger
/wildfly-24.0.0.Final/standalone/log/holidaysystem.log

### Mail
help.holiday.request@gmail.com
1Vaction2!Request%

### Message system
For the Message Broker, we use ActiveMQ Artemis 
that comes embedded within WildFly.

### Exception handling
UncaughtExceptionMapper



### database

- In [WILDFLY_HOME]/modules add /org/postgresql/main
- In main folder add module.xml and postgresql-42.3.3.jar from [poject root]/wildfly folder
- Using Wildfly deployment add postgresql-42.3.3.jar


postgresql 42.3.3
https://jdbc.postgresql.org/download.html

Host: ec2-52-214-125-106.eu-west-1.compute.amazonaws.com

Database: dfejis77nibjdr

User: qexdvxvgdqtdgh

Port: 5432

Password: bf7f8df51b60c17d1fcc333c3f7b63e57df7300022deb67d60eb6cee2bd4e5dd

jdbc:postgresql://ec2-52-214-125-106.eu-west-1.compute.amazonaws.com:5432/dfejis77nibjdr

Heroku CLI: heroku pg:psql postgresql-infinite-73558 --app holiday-system
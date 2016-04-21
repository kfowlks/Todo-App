Simple Todo Application Stripes + Spring + Sql2o + HSQLDB 
=================================================

The intent of this project is demostrate a simple java stack that can be deployed and run locally using common tools. This application attempts to 
show the simplistty of Java when used with moderen tools and frameworks. 

Gradle is use for the dependency managment

>*** Important ***
> You also need JDK 1.7
* * *


### Reference

* View App URL  [https://localhost:10101/Todo-App]

### Version
1.0

### Tech

* [Gradle]          - Build Manager
* [Jetty]           - Jetty 9
* [Bootstrap]       - BootStrap via WebJar's
* [HSQLDB]          - Database
* [Sql2o]           - Persistence Layer 
* [Spring]          - Dependency Injection


### External Dependency
* None

### Installation
```bash

./git clone git@github.com:kfowlks/Todo-App.git

```
The artifacts should be now available at the below location

/home2/Todo-App/[sub-project name]/build/libs

Run the below web app by executing the below
```bash

gradle Todo-App-Web:run
```

### Create Database HSQLDB
```bash

cd /home2/Todo-App

java -cp lib/hsqldb.jar org.hsqldb.util.DatabaseManagerSwing

```

Execute SQL Scripts in the below folder
C:\home2\Todo-App\Todo-App-Web\src\main\resources\db\migration

V1_0__initial_load.sql

Updated the path of the database by editing the applicationContext.xml found in the below directory

C:\home2\Todo-App\Todo-App-Web\src\main\webapp\WEB-INF




gradle Todo-App-Web:run
 
Simple Todo-App Application (Stripes + Spring + Sql2o + H2)
=================================================

The intent of this project is demonstrate a simple java stack that can be deployed and run locally using common tools. This application attempts to show the simplistic nature of Java when used with modern tools/framework

Gradle is use for the dependency management

>*** Important ***
> You also need JDK 1.7
* * *

### Reference

* [View Application]: http://localhost:10101/Todo-App

### Version
1.0

### Tech

* [Gradle]          - Build Manager
* [Jetty]           - Jetty 9
* [Bootstrap]       - BootStrap via WebJar's
* [H2]      	    - In Memory Database
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

Run the below web application by executing the below
```bash

gradle Todo-App-Web:run
```

Run the below tests by executing the below
```bash

gradle Todo-App-Web:test
```

[![Travis build status](https://travis-ci.org/kfowlks/Todo-App.svg?branch=master)](https://travis-ci.org/kfowlks)

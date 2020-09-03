
README_QUARKUS.txt explains how to:
 - scaffold a project in a single command line
 - enable the development mode (hot reload)
 - import the project in your favorite IDE
 - and more

##  Create the application:
mvn io.quarkus:quarkus-maven-plugin:1.7.1.Final:create -DprojectGroupId=it.example.services -DprojectArtifactId=employee-service -DclassName="it.example.services.employee.controller.EmployeeController" -Dpath="/employees" -Dextensions="resteasy-jackson, hibernate-validator, rest-client, resteasy"

## Add future dependencies:
mvn quarkus:add-extension -Dextensions="hibernate-orm, jdbc-mysql"
mvn quarkus:add-extension -Dextensions="metrics"

## Import on eclipse:
mvn eclipse:eclipse

## Compile and run on runs Quarkus in development mode, which means that you don't have to quit and restart Quarkus during development.
## This enables hot deployment with background compilation, which means that when you modify your Java files and/or your resource files 
## and refresh your browser, these changes will automatically take effect. This works too for resource files like the configuration property file. 
## Refreshing the browser triggers a scan of the workspace, and if any changes are detected, the Java files are recompiled and the application 
## is redeployed; your request is then serviced by the redeployed application. If there are any issues with compilation or deployment an error 
## page will let you know.
mvnw compile quarkus:dev

## Run test
mvnw test

## Access quarkus application on:
http://localhost:8081/my-page.html

## Call quarkus JSON microservices:
http://localhost:8081/employees

## 

##

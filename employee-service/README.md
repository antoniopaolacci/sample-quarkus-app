# employee microservice

_README.md_ explains how to:
 - scaffold a project in a single command line  (bootstrapping quarkus project)
 - enable the development mode (hot reload)
 - import the project in your IDE (Eclipse)
 - and more

#####  Create the application:
`mvn io.quarkus:quarkus-maven-plugin:1.7.1.Final:create -DprojectGroupId=it.example.services -DprojectArtifactId=employee-service -DclassName="it.example.services.employee.controller.EmployeeController" -Dpath="/employees" -Dextensions="resteasy-jackson, hibernate-validator, rest-client, resteasy"`

##### Add other dependencies:

Inspect POM file, some dependencies are added.

`mvnw quarkus:add-extension -Dextensions="hibernate-orm, jdbc-mysql"`
`mvnw quarkus:add-extension -Dextensions="metrics"`
`mvnw quarkus:add-extension -Dextensions="health"`

In a test environment we want to use an H2 database:

`mvnw quarkus:add-extension -Dextensions="jdbc-h2"`

##### Import on Eclipse IDE:
`mvn eclipse:eclipse`

##### Compile and run in development mode:
`mvnw compile quarkus:dev`

##### Run test:
`mvnw test`

##### Access quarkus application on:
- Landing page:  http://localhost:8081/my-page.html
- Rest endpoint:  http://localhost:8081/employees



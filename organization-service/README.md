_README.md_ explains how to:

 - scaffold a project in a single command line  (bootstrapping quarkus project)
 - enable the development mode (hot reload)
 - import the project in your IDE (Eclipse)
 - and more

###### Create quarkus project:

```
mvn io.quarkus:quarkus-maven-plugin:1.7.1.Final:create -DprojectGroupId=it.example.services -DprojectArtifactId=organization-service -DclassName="it.example.services.organization.controller.OrganizationController" -Dpath="/organizations" -Dextensions="resteasy-jackson, hibernate-validator, rest-client, resteasy, hibernate-orm, jdbc-mysql, metrics, health"
```

##### Import on Eclipse IDE:

`mvn eclipse:eclipse`

##### Compile and run in development mode:

`mvnw compile quarkus:dev`

##### Run test:

`mvnw test`

##### Access quarkus application on:

- Landing page:  http://localhost:8085/index.html
- Rest endpoint:  http://localhost:8085/organizations
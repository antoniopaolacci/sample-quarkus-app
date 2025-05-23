# employee microservice

_README.md_  explains how to:
 - scaffold a project in a single command line  (bootstrapping quarkus project)
 - enable the development mode (hot reload)
 - import the project in your IDE (Eclipse)
 - and more

#####  Create the application:
`mvn io.quarkus:quarkus-maven-plugin:1.7.1.Final:create -DprojectGroupId=it.example.services -DprojectArtifactId=employee-service -DclassName="it.example.services.employee.controller.EmployeeController" -Dpath="/employees" -Dextensions="resteasy-jackson, hibernate-validator, rest-client, resteasy"`

##### Add other dependencies:

Inspect POM file, some dependencies are added.

`mvnw quarkus:add-extension -Dextensions="hibernate-orm, jdbc-mysql, jdbc-postgresql"`

`mvnw quarkus:add-extension -Dextensions="metrics"`

`mvnw quarkus:add-extension -Dextensions="health"`

`mvnw quarkus:add-extension -Dextensions="smallrye-opentracing"`

`mvn quarkus:add-extension -Dextensions="security-oauth2"`

In a test environment we want to use an H2 database:

`mvnw quarkus:add-extension -Dextensions="jdbc-h2"`

##### Import on Eclipse IDE:
`mvn eclipse:eclipse`

##### Compile and run in development mode:
`mvnw compile quarkus:dev`

##### Run test:
`mvnw test`

##### Access quarkus application metrics and health:

- http://localhost:8081/metrics
- http://localhost:8081/health
- http://localhost:8081/health/live  
- http://localhost:8081/health/ready

##### Access quarkus application on:
- Landing page:  http://localhost:8081/employee-page.html
- Rest endpoint:  http://localhost:8081/employees
- Swagger:  http://localhost:8081/swagger-ui

#### Kubernetes

Add quarkus dependencies, build and deply

`mvnw quarkus:add-extension -Dextensions="quarkus-kubernetes, quarkus-container-image-docker, quarkus-kubernetes-config"`

- `quarkus-kubernetes` (extension provide support mechanisms for generating Kubernetes manifests, deploying them on the platform)

- `quarkus-container-image-docker` (to build image from Dockerfile on `/src/main/docker/`)


Create kubernetes manifest

`mvnw package`  (find yaml on */employee-service/target/kubernetes/*)

Deploy on kubernetes cluster and publish image on *docker.io* registry

`mvnw clean package -Dquarkus.container-image.build=true -Dquarkus.container-image.push=true -Dquarkus.kubernetes.deploy=true`

![image](https://github.com/antoniopaolacci/sample-quarkus-app/blob/master/employee-service/deploy.jpg)


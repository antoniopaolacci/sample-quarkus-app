# organization microservice

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
- Swagger:  http://localhost:8085/swagger-ui


#### Kubernetes

Add quarkus dependencies, build and deply

`mvnw quarkus:add-extension -Dextensions="quarkus-kubernetes, quarkus-container-image-docker, quarkus-kubernetes-config"`

- `quarkus-kubernetes` (extension provide support mechanisms for generating Kubernetes manifests, deploying them on the platform)

- `quarkus-container-image-docker` (to build image from Dockerfile on `/src/main/docker`\)


Create kubernetes manifest

`mvnw package`  (find it on */department-service/target/kubernetes*)

Deploy on kubernetes cluster and publish image on *docker.io* registry

`mvnw clean package -Dquarkus.container-image.build=true -Dquarkus.container-image.push=true -Dquarkus.kubernetes.deploy=true`

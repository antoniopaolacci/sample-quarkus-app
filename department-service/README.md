# department microservice

_README.md_   explains how to:

 - scaffold a project in a single command line  (bootstrapping quarkus project)
 - enable the development mode (hot reload)
 - import the project in your IDE (Eclipse)
 - and more

#####  Create the application:

`mvn io.quarkus:quarkus-maven-plugin:1.7.1.Final:create -DprojectGroupId=it.example.services -DprojectArtifactId=department-service -DclassName="it.example.services.department.controller.DepartmentController" -Dpath="/departments" -Dextensions="resteasy-jackson, hibernate-validator, rest-client, resteasy"`

#### Add some dependencies:
`mvnw quarkus:add-extension -Dextensions="smallrye-opentracing"`

#### Add persistence dependencies:
`mvnw quarkus:add-extension -Dextensions="hibernate-orm, jdbc-mysql, jdbc-postgresql, jdbc-h2"`

##### Import on Eclipse IDE:

`mvn eclipse:eclipse`

##### Compile and run in development mode:

`mvnw compile quarkus:dev`

##### Run test:

`mvnw test`

##### Access quarkus application on:

- Landing page:  http://localhost:8083/index.html
- Rest endpoint:  http://localhost:8083/departments
- Swagger:  http://localhost:8083/swagger-ui


#### Kubernetes

Add quarkus dependencies, build and deply

`mvnw quarkus:add-extension -Dextensions="quarkus-kubernetes, quarkus-container-image-docker, quarkus-kubernetes-config"`

- `quarkus-kubernetes` (extension provide support mechanisms for generating Kubernetes manifests, deploying them on the platform)

- `quarkus-container-image-docker` (to build image from Dockerfile on `/src/main/docker/`\)

- `quarkus-kubernetes-config`  (with Kubernetes Config extension you can use `ConfigMap` as a configuration source, without having to mount them into the pod with the application)


Create kubernetes manifest yaml

`mvnw package`  (find yaml on */department-service/target/kubernetes/*)

Deploy on kubernetes cluster and publish image on *docker.io* registry

`mvnw clean package -Dquarkus.container-image.build=true -Dquarkus.container-image.push=true -Dquarkus.kubernetes.deploy=true`


![image](https://github.com/antoniopaolacci/sample-quarkus-app/blob/master/department-service/deploy.jpg)


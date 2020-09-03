# sample-quarkus-application

**Quarkus** framework It allows us to automatically generate Kubernetes resources based on the defaults and user-provided configuration. It also provides an extension for building and pushing container images, then deploying the application to the target chosen platform: minikube, docker desktop, digital ocean, Google Kubernetes Engine GKE, etc. Quarkus framework is designed for building Java applications in times of microservices and  serverless architectures.  If you compare it with other popular frameworks like  Spring Boot / Spring Cloud (Netflix) or Micronaut, the first difference is native support for running on Kubernetes or Openshift platforms. 


In java parent POM progect create a simple quarkus REST microservice with maven _employee-service_:

`mvn io.quarkus:quarkus-maven-plugin:1.7.1.Final:create -DprojectGroupId=it.example.services -DprojectArtifactId=employee-service -DclassName="it.example.services.employee.controller.EmployeeController" -Dpath="/employees" -Dextensions="resteasy-jackson, hibernate-validator, rest-client, resteasy"`

A list of dependencies that need to be used in pom.xml, should be declared in parameter `-Dextensions`

Run locally 

`mvnw compile quarkus:dev`

Consume _employee-service_ microservice access to webpage:

Access the quarkus served homepage and navigate to _employee-service_ web page:

- http://localhost:8081/my-page.html

- http://localhost:8081/employees

References:

- Quarkus Startup [https://quarkus.io/guides/getting-started](https://quarkus.io/guides/getting-started)

- Rest Quarkus Microservice [https://quarkus.io/guides/rest-json](https://quarkus.io/guides/rest-json)

- Eclipse IDE Quarkus Setup https://quarkus.io/blog/eclipse-got-quarkused/

- Docker Quarkus https://quarkus.io/guides/container-image

- Kubernetes Quarkus Example https://developers.redhat.com/blog/2020/04/24/ramp-up-on-quarkus-a-kubernetes-native-java-framework/ 


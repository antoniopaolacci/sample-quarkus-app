# sample-quarkus-app
**Quarkus** framework It allows us to automatically generate Kubernetes resources based on the defaults and user-provided configuration. It also provides an extension for building and pushing container images, then deploying the application to the target chosen platform: minikube, docker desktop, digital ocean, Google Kubernetes Engine GKE, etc.

Quarkus framework is designed for building Java applications in times of microservices and  serverless architectures.  If you compare it with other popular frameworks like  Spring Boot / Spring Cloud (Netflix) or Micronaut, the first difference is native support for running on Kubernetes or Openshift platforms. 

Create a simple quarkus REST microservice with maven:

`mvn io.quarkus:quarkus-maven-plugin:1.7.1.Final:create -DprojectGroupId=it.example.services -DprojectArtifactId=employee-service -DclassName="it.example.services.employee.controller.EmployeeController" -Dpath="/employees" -Dextensions="resteasy-jackson, hibernate-validator, rest-client, resteasy"`

A list of dependencies that need to be used in pom.xml, should be declared in parameter `-Dextensions`


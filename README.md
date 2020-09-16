# sample-quarkus-application

##### Prerequisites

- using Apache Maven >= 3.6.3
- using Java version >= 1.8  (`quarkus:dev` complains, _Using Java versions older than 11 to build Quarkus applications is deprecated and will be disallowed in a future release!_)
- if use eclipse IDE, install quarkus plugin from marketplace 

**Quarkus** framework It allows us to automatically generate Kubernetes resources based on the defaults and user-provided configuration. It also provides an extension for building and pushing container images, then deploying the application to the target chosen platform: minikube, docker desktop kubernetes, digital ocean, Google Kubernetes Engine GKE, etc.

**Quarkus** framework is designed for building Java applications in times of microservices and serverless architectures.  If you compare it with other popular frameworks like  Spring Boot / Spring Cloud (Netflix), the first difference is native support for running on Kubernetes or Openshift container orchestration platforms. BTW quarkus provides following benefits:

- scaffold a project in a single command line
- enable the *development mode* (hot reload)
- example Dockerfile files for both _native_ and _jvm_ modes in `src/main/docker`. Instructions to build the image and run the container are written in those Dockerfiles.
- automatically serves static resources located under the `src/main/resources/META-INF/resources` directory
- an associated unit test 
- and more

**Bootstrap**

In a java parent POM project create a simple quarkus REST microservice with maven, called it _employee-service_:

`mvn io.quarkus:quarkus-maven-plugin:1.7.1.Final:create -DprojectGroupId=it.example.services -DprojectArtifactId=employee-service -DclassName="it.example.services.employee.controller.EmployeeController" -Dpath="/employees" -Dextensions="resteasy-jackson, hibernate-validator, rest-client, resteasy"`

A list of dependencies that need to be used in pom.xml, should be declared in parameter `-Dextensions`

For example add to our project future-use dependancies:

`mvn quarkus:add-extension -Dextensions="hibernate-orm, jdbc-mysql"
mvn quarkus:add-extension -Dextensions="metrics"`

**Obtain list of dependencies**

`mvnw quarkus:list-extensions`

**Test**

Test locally  (quarkus provide junit5, REST-assured and use Hamcrest matchers to do assertion)

`mvnw test`

**Run**

Run locally 

`mvnw compile quarkus:dev`

`quarkus:dev` enables hot deployment with background compilation, which means that when you modify your Java files or your resource files and refresh  your browser these changes will automatically take effect.

**Consume**

Consume _employee-service_ microservice access to webpage:

Access the quarkus served landing page and navigate to _employee-service_ web page to test this microservices architecture github process:

- http://localhost:8081/index.html
- http://localhost:8081/employees

The same steps are repeatable for microservices projects: 

- _department-service_ 

- _organization-services_

  

**Kubernetes**

Quarkus can use the project *Dekorate* to generate an opinionated base Kubernetes resource.
`./mvnw quarkus:add-extension -Dextensions="quarkus-kubernetes"`
When running the `./mvnw package` the Kubernetes resources are created in the t*arget/*
*wiring-classes/META-INF/kubernetes/* directory.



**References**

- Quarkus Startup [https://quarkus.io/guides/getting-started](https://quarkus.io/guides/getting-started)
- Rest Quarkus Microservice [https://quarkus.io/guides/rest-json](https://quarkus.io/guides/rest-json)
- Eclipse IDE Quarkus Setup https://quarkus.io/blog/eclipse-got-quarkused/
- Docker Quarkus https://quarkus.io/guides/container-image
- Kubernetes Quarkus Example https://developers.redhat.com/blog/2020/04/24/ramp-up-on-quarkus-a-kubernetes-native-java-framework/ 
- Logging https://quarkus.io/guides/logging


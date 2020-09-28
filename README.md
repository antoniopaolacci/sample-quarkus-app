# sample-quarkus-application

##### Prerequisites

- using Apache Maven >= 3.6.3
- using Java version >= 1.8  (`mvnw quarkus:dev` complains, _Using Java versions older than 11 to build Quarkus applications is deprecated and will be disallowed in a future release!_)
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

> Using **Quarkus** for building REST application that connects to Mysql database using Hibernate ORM and communicate with others microservices. our goals are: to rapid test it throught mockup and an in-memory persistence switch, to interact with rest web services through angular/html pages,  to take care of logging, OAuth2 for securing endpoints...

In a java parent POM directory create a simple quarkus REST microservice with maven, called it _employee-service_:

`mvn io.quarkus:quarkus-maven-plugin:1.7.1.Final:create -DprojectGroupId=it.example.services -DprojectArtifactId=employee-service -DclassName="it.example.services.employee.controller.EmployeeController" -Dpath="/employees" -Dextensions="resteasy-jackson, hibernate-validator, rest-client, resteasy"`

A list of dependencies that need to be used in *pom.xml* for our project should be declared in parameter `-Dextensions`

For example add for future-use dependancies:

- Mysql and Hibernate support

`mvn quarkus:add-extension -Dextensions="hibernate-orm, jdbc-mysql"`

or for example to trace on logs:

- OpenTracing support

`mvnw quarkus:add-extension -Dextensions="smallrye-opentracing"`   

**Obtain list of dependencies**

`mvnw quarkus:list-extensions`

**Test**

Test locally  (quarkus provide *junit5*, *REST-assured* and use *Hamcrest matchers* to do assertion)

`mvnw test`

**Run**

Run locally 

`mvnw compile quarkus:dev`

`quarkus:dev` enables hot deployment with background compilation, which means that when you modify your Java files or your resource files and refresh  your browser these changes will automatically take effect.

**Consume**

To Consume _employee-service_ microservice access the webpage:

Access the quarkus served landing page and navigate to _employee-service_ web page to test this microservices architecture github process:

- http://localhost:8081/index.html
- http://localhost:8081/employees

if you access an incorrect address a *"404 NOT FOUND"* .html page display all the Rest endpoint, something similar to Swagger.

The same are repeatable for microservices projects: 

- _department-service_ 

- _organization-services_



**Kubernetes**

Quarkus does not implement mechanisms for generating Kubernetes xml, deploying them on the platform, or building images. It adds  some logic to the existing tools. To enable extensions to Dekorate and  Jib we should include the following dependencies. 

Quarkus can use the external project *Dekorate* to generate an opinionated base Kubernetes resource.

`./mvnw quarkus:add-extension -Dextensions="quarkus-kubernetes"`

`./mvnw quarkus:add-extension -Dextensions="quarkus-container-image-jib"` or use an alternative extension 

`"quarkus-container-image-docker"`(with this dependency quarkus use Docker binary and the generated Dockerfiles under `src/main/docker` in order to perform Docker builds)

When running the `./mvnw package` the Kubernetes resources are created in the t*arget/*
*wiring-classes/META-INF/kubernetes/* directory.

```xml
POM.xml

...
<dependency>
   <groupId>io.quarkus</groupId>
   <artifactId>quarkus-kubernetes</artifactId>
</dependency>
<!-- Quarkus use Docker binary and the generated Dockerfiles under "src/main/docker" -->
<dependency>
   <groupId>io.quarkus</groupId>
   <artifactId>quarkus-container-image-docker</artifactId>
</dependency>

<!-- or in alternative use extension "quarkus-container-image-jib" and Quarkus use Jib to build docker image-->
<!--
<dependency>
   <groupId>io.quarkus</groupId>
   <artifactId>quarkus-container-image-jib</artifactId>
</dependency> 
-->

```

*Jib* library builds optimized images for Java applications without a Docker daemon, and without deep mastery of Docker best-practices. 

*Dekorate* is a library that makes generating and decorating Kubernetes manifests**[*]** in a simpler manner.  It may generate *.yaml* file basing on the source code, annotations, and  configuration properties.

**[*]** Kubernetes manifests files are used to create, modify and delete  Kubernetes resources such as pods, deployments, services or ingresses.  It is very common to define manifests in form of `.yaml` files and send them to the Kubernetes API Server via commands such as 

`kubectl apply -f my-manifest-k8s-file.yaml` 

or `kubectl delete -f my-manifest-k8s-file.yaml`



**Build Docker container and deploy on Kubernetes cluster**  

In order to build a Docker image with the application, we need to enable option `quarkus.container-image.build` during Maven build. If you also want to deploy and run a container with the application on your local Kubernetes instance you need to enable option `quarkus.kubernetes.deploy`.

`mvnw clean package -Dquarkus.container-image.build=true -Dquarkus.kubernetes.deploy=true`

if you want to push on docker registry:

`mvnw clean package -Dquarkus.container-image.build=true -Dquarkus.container-image.push=true -Dquarkus.kubernetes.deploy=true`



**References**

- Quarkus Startup [https://quarkus.io/guides/getting-started](https://quarkus.io/guides/getting-started)
- Rest Quarkus Microservice [https://quarkus.io/guides/rest-json](https://quarkus.io/guides/rest-json)
- Eclipse IDE Quarkus Setup https://quarkus.io/blog/eclipse-got-quarkused/
- Docker Quarkus https://quarkus.io/guides/container-image
- Kubernetes Quarkus Example https://developers.redhat.com/blog/2020/04/24/ramp-up-on-quarkus-a-kubernetes-native-java-framework/ 
- Logging https://quarkus.io/guides/logging 
- Tracing https://quarkus.io/guides/opentracing
- App Configuration https://antoniogoncalves.org/2019/06/07/configuring-a-quarkus-application/
- OAuth2 and Keycloak  https://piotrminkowski.com/2020/09/16/quarkus-oauth2-and-security-with-keycloak/


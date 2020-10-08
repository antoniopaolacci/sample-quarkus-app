# sample-quarkus-application



---------------------------------------------------------------------------------------------------------------

**Prerequisites**

- using Apache **Maven >= 3.6.3**
- using **Java version >= 1.8**  (`mvnw quarkus:dev` complains, _Using Java versions older than 11 to build Quarkus applications is deprecated and will be disallowed in a future release!_)
- if use **eclipse** IDE, install **quarkus plugin** from marketplace 

----------------------------------------------------------------------------------------------------------------

**Quarkus** framework It allows us to automatically generate Kubernetes resources based on the defaults and user-provided configuration. It also provides an extension for building and pushing container images, then deploying the application to the target chosen platform: minikube, docker desktop kubernetes, digital ocean, Google Kubernetes Engine GKE, etc.

**Quarkus** framework is designed for building Java applications in times of microservices and serverless architectures.  If you compare it with other popular frameworks like  Spring Boot / Spring Cloud (Netflix), the first difference is native support for running on Kubernetes or Openshift container orchestration platforms. BTW quarkus provides following benefits:

- scaffold a project in a single command line

- enable the *development mode* (hot reload)

- example Dockerfile files for both _native_ and _jvm_ modes in `src/main/docker`. Instructions to build the image and run the container are written in those Dockerfiles.

- automatically serves static resources located under the `src/main/resources/META-INF/resources` directory

- an associated unit test 

- and more

  

**Bootstrap** and **Scaffolding**

> Using **Quarkus** for building REST application that connects to Mysql database using Hibernate ORM and communicate with others microservices. our goals are: to rapid test it throught mockup and an in-memory persistence switch, to interact with rest web services through angular/html pages,  to take care of logging, OAuth2 for securing rest endpoints, etc

In a java parent POM directory create a simple quarkus REST microservice with maven, called it _employee-service_:

`mvn io.quarkus:quarkus-maven-plugin:1.7.1.Final:create -DprojectGroupId=it.example.services -DprojectArtifactId=employee-service -DclassName="it.example.services.employee.controller.EmployeeController" -Dpath="/employees" -Dextensions="resteasy-jackson, hibernate-validator, rest-client, resteasy"`

A list of dependencies that need to be used in *pom.xml* for our project should be declared in parameter `-Dextensions`

For example add for future-use dependancies:

- Mysql and Hibernate support

`mvn quarkus:add-extension -Dextensions="hibernate-orm, jdbc-mysql"`

or for example to trace on logs:

- OpenTracing support

`mvnw quarkus:add-extension -Dextensions="smallrye-opentracing"`   

**List of POM dependencies**

`mvnw quarkus:list-extensions`

**Test**

Test locally  (quarkus provide *junit5*, *REST-assured* and use *Hamcrest matchers* to do assertion)

`mvnw test`

**Run**

Run locally  

`mvnw compile quarkus:dev`

`quarkus:dev` enables *hot deployment* with background compilation, which means that when you modify your Java files or your resource files and refresh  your browser these changes will automatically take effect.

Quarkus profiles are `dev` (when running `quarkus:dev` goal), `test` (when running tests) and `prod` (when not the others).

**Consume**

To Consume _employee-service_ microservice access the webpage:

Access the quarkus served landing page and navigate to _employee-service_ web page to test this microservices architecture github process:

- http://localhost:8081/index.html
- http://localhost:8081/employees

if you access an incorrect address a *"404 NOT FOUND"* .html page display all the Rest endpoint, something similar to Swagger.

The same are repeatable for microservices projects: 

- _department-service_ 
- _organization-services_



-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

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



----------------------------------------------------------------------------------------------------------------------------------------------------

**OAuth2**

> Quarkus OAuth2 support is based on the WildFly Elytron Security project. Using Quarkus OAuth2 extension to provide RBAC authorization based on integration with Keycloak. 



Include Quarkus modules for OAuth2 or use `mvn quarkus:add-extension -Dextensions="security-oauth2"`

```xml
<dependency>
   <groupId>io.quarkus</groupId>
   <artifactId>quarkus-elytron-security-oauth2</artifactId>
</dependency>
```



Quarkus OAuth2 provides a set of annotations for setting permissions. We can allow to call an endpoint by any user with `@PermitAll` annotation. The annotation `@DenyAll` indicates that the given endpoint cannot be accessed by anyone. We can  also define a list of roles allowed for calling a given endpoint with `@RolesAllowed`. The controller contains different types of CRUD methods. I defined three roles: `viewer`, `manager`, and `admin`. The `viewer` role allows calling only GET methods. The `manager` role allows calling GET and POST methods. 

| Roles     | Http Methods | Java Controller Methods                                      | Rest URL                                                     |
| --------- | ------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `viewer`  | GET          | findById, findAll, findAllByDepartment, findAllByOrganization | /employees, /employees/id, /employees/department/id,  /employees/organization/id |
| `manager` | GET, POST    | add, update, delete                                          | /employees, /employees/id                                    |
| `admin`   | GET, POST    | manageUser                                                   | /app/manage, /app/test                                       |



Running KeyCloak on docker container:

```bash
docker network create keycloak-network

docker run -d --name postgres --net keycloak-network -e POSTGRES_DB=keycloak -e POSTGRES_USER=keycloak -e POSTGRES_PASSWORD=password postgres

docker run -d --name keycloak -p 8080:8080 -p 8443:8443 --net keycloak-network -e DB_USER=keycloak -e DB_PASSWORD=password -e DB_VENDOR=POSTGRES -e DB_ADDR=postgres -e DB_DATABASE=keycloak -e KEYCLOAK_USER=keycloak -e KEYCLOAK_PASSWORD=password -e PROXY_ADDRESS_FORWARDING=true jboss/keycloak
```

Go to the Keycloak Admin Console and login with the username and password you created earlier.

First, we need to create a client with a given name. Let’s say this name is `quarkus`. The client credentials are used during the authorization process. It is important to choose `confidential` in the “`Access Type”` section and enable option “`Direct Access Grants`”.

Then we may switch to the “`Credentials`” tab, and copy the client `Secret`. 

Follow subsequent images to config Keycloak at: https://github.com/antoniopaolacci/sample-quarkus-app/tree/master/images



Setting configuration properties on Quarkus OAuth2 module:

```properties
quarkus.oauth2.client-id=quarkus
quarkus.oauth2.client-secret=7dd4d516-e06d-4d81-b5e7-3a15debacebf
quarkus.oauth2.introspection-url=http://localhost:8888/auth/realms/master/protocol/openid-connect/token/introspect
quarkus.oauth2.role-claim=roles
```



Setting annotations on controller methods:

```java
    @PermitAll
    public List<Employee> findAll() {   ... }
    
    @RolesAllowed(value = { "manager", "admin", "viewer" })
    public Employee findById(@PathParam("id") Long id) {     ...   }
    
```



Run some test to our backend microservice:

```bash
curl -X POST http://localhost:8080/auth/realms/master/protocol/openid-connect/token -d "grant_type=password" -d "client_id=quarkus" -d "client_secret=ec920554-11df-4524-87f1-46db53712371" -d "username=test_viewer" -d "password=123456"

{
	"access_token": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJxV1h3ZlJDNTJuQWN5QzJNVGJYeHNlT1ZtWndrRXV6Z0kxUnRqZXBubVFNIn0.eyJleHAiOjE2MDIwNjY1NTMsImlhdCI6MTYwMjA2NjI1MywianRpIjoiZDMwNDljNWItM2I0Ny00ZWQxLWI5MTQtYTE4ZTYwNTA1NmJiIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL21hc3RlciIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI5OGNmMDk3OS05MGViLTQwNTMtOGVmOC03NTFmNDJmNzEyN2IiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJxdWFya3VzIiwic2Vzc2lvbl9zdGF0ZSI6IjViOGM4ZWFlLWUyYTUtNGMzZC05ZDA3LWM2ODVmYTI4M2RlMCIsImFjciI6IjEiLCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJyb2xlcyI6WyJ2aWV3ZXIiLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl0sInByZWZlcnJlZF91c2VybmFtZSI6InRlc3Rfdmlld2VyIiwiZW1haWwiOiJ0ZXN0X3ZpZXdlckBleGFtcGxlLmNvbSJ9.bKPHxJGKwIwHSOWA0oS7P5a3_vYgznrc2losoz3quip3AxtTGvE0r1Nr5tm-OCnmEBR0eWxJBFOh-5AY_OfXI9HirpHtXYhojDSJ4QL_4Orf7LB3H2X4drWU5hpPYUg5CLiQHWqG0QUWyRzWIog_O0mdWXVKRdE3XEMYawprQ-dLdtV8W0SsfT9aD2NxbYHBQRHjylvVIArqI-DhI8i54-LGnYBsgtPmOJVtiSafw28XLlJF49lao7GG72E2x6pG09uZZK2nptnFwaBPuTLzsjBjv9l7LFVJuRaaBPt559_ylENuHaLSw4pve-dfx6EHsB33iED2MvhsFTrQk-Yx_A",
	"expires_in": 300,
	"refresh_expires_in": 1800,
	"refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJjMDFlNGQyOC0wODIxLTQ3OWQtOTJlYi02MTZlZDNmZTQyZWQifQ.eyJleHAiOjE2MDIwNjgwNTMsImlhdCI6MTYwMjA2NjI1MywianRpIjoiNzdiNTliNjktZGU4Ny00NzEzLTg5NDYtMThhMmIxODhjZTcxIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL21hc3RlciIsImF1ZCI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hdXRoL3JlYWxtcy9tYXN0ZXIiLCJzdWIiOiI5OGNmMDk3OS05MGViLTQwNTMtOGVmOC03NTFmNDJmNzEyN2IiLCJ0eXAiOiJSZWZyZXNoIiwiYXpwIjoicXVhcmt1cyIsInNlc3Npb25fc3RhdGUiOiI1YjhjOGVhZS1lMmE1LTRjM2QtOWQwNy1jNjg1ZmEyODNkZTAiLCJzY29wZSI6ImVtYWlsIHByb2ZpbGUifQ.5TaxcJfgXiuZGEcHSDv0TvJd49TML86cpY3Xa-MrakI",
	"token_type": "bearer",
	"not-before-policy": 0,
	"session_state": "5b8c8eae-e2a5-4c3d-9d07-c685fa283de0",
	"scope": "email profile"
}

curl -v http://localhost:8081/employees/1 -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJxV1h3ZlJDNTJuQWN5QzJNVGJYeHNlT1ZtWndrRXV6Z0kxUnRqZXBubVFNIn0.eyJleHAiOjE2MDIwNjY1NTMsImlhdCI6MTYwMjA2NjI1MywianRpIjoiZDMwNDljNWItM2I0Ny00ZWQxLWI5MTQtYTE4ZTYwNTA1NmJiIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2F1dGgvcmVhbG1zL21hc3RlciIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI5OGNmMDk3OS05MGViLTQwNTMtOGVmOC03NTFmNDJmNzEyN2IiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJxdWFya3VzIiwic2Vzc2lvbl9zdGF0ZSI6IjViOGM4ZWFlLWUyYTUtNGMzZC05ZDA3LWM2ODVmYTI4M2RlMCIsImFjciI6IjEiLCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJyb2xlcyI6WyJ2aWV3ZXIiLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl0sInByZWZlcnJlZF91c2VybmFtZSI6InRlc3Rfdmlld2VyIiwiZW1haWwiOiJ0ZXN0X3ZpZXdlckBleGFtcGxlLmNvbSJ9.bKPHxJGKwIwHSOWA0oS7P5a3_vYgznrc2losoz3quip3AxtTGvE0r1Nr5tm-OCnmEBR0eWxJBFOh-5AY_OfXI9HirpHtXYhojDSJ4QL_4Orf7LB3H2X4drWU5hpPYUg5CLiQHWqG0QUWyRzWIog_O0mdWXVKRdE3XEMYawprQ-dLdtV8W0SsfT9aD2NxbYHBQRHjylvVIArqI-DhI8i54-LGnYBsgtPmOJVtiSafw28XLlJF49lao7GG72E2x6pG09uZZK2nptnFwaBPuTLzsjBjv9l7LFVJuRaaBPt559_ylENuHaLSw4pve-dfx6EHsB33iED2MvhsFTrQk-Yx_A"


 {
    "id":1,
    "name":"John Smith",
    "age":30,"position":"Kubernetes Developer",
    "organizationId":1,
    "departmentId":1
 }
```



-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

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


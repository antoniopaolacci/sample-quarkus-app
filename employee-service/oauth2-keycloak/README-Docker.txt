
##############
##  Docker  ##
##############
docker network create keycloak-network
docker run -d --name postgres --net keycloak-network -e POSTGRES_DB=keycloak -e POSTGRES_USER=keycloak -e POSTGRES_PASSWORD=password postgres
docker run -d --name keycloak -p 8080:8080 -p 8443:8443 --net keycloak-network -e DB_USER=keycloak -e DB_PASSWORD=password -e DB_VENDOR=POSTGRES -e DB_ADDR=postgres -e DB_DATABASE=keycloak -e KEYCLOAK_USER=keycloak -e KEYCLOAK_PASSWORD=password -e PROXY_ADDRESS_FORWARDING=true jboss/keycloak
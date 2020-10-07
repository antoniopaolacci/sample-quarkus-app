
## Expose employee-service microservices
kubectl expose deployment employee-service --port=8765 --target-port=8081 --type=LoadBalancer --name=employee-service

## Show kubernetes services
kubectl get services

NAME                      TYPE           CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
employee-service          LoadBalancer   10.102.136.198   localhost     8765:30850/TCP   10s
kubernetes                ClusterIP      10.96.0.1        <none>        443/TCP          109d

## Test running
curl localhost:8765

## Access employees html page
curl localhost:8765/employee-page.html

## Tail log
kubectl logs -f employee-service-677d95646d-vb9nn

## Add a config map and labelling
kubectl create configmap employee-config --from-file=config/employee.properties
kubectl label --overwrite configmap employee-config app-type=demo app.kubernetes.io/name=employee-service app.kubernetes.io/version="1.1"

## Restart a POD tips
kubectl scale deployment <name-of-deployment> --replicas=0
kubectl scale deployment <name-of-deployment> --replicas=1
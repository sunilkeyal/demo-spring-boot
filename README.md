# spring-boot-minimal

This is a simple spring boot application with no other dependencies
So that it can be deployed in Docker, Kubernetes, Azure etc.

Install the following VS Code Extensions
## VS Code Extensions
- Extension Pack for Java (This will install the following)
  - Language Support for Java
  - Debugger for Java
  - Test Runner for Java
  - Maven for Java
  - Gradle for Java
  - Project Manager for Java
  - IntelliCode
- Java extension pack (This will install the following)
  - Maven for Java
  - Language Support for Java
  - Debugger for Java
  - Test Runner for java
  - Extension Pack for Java
  - Project Manager for Java
  -  Spring initializer Java Support
  -  Java language support
- Spring Boot extension pack
  - spring iniializer java support
  - spring boot tools
  - spring boot dashboard

## Install spring-boot-minimal in docker container using Dockerfile
- ./gradlew clean build
- docker rm --force minimal (deletes the container if exists)
- docker image ls -a (view list of images)
- docker rmi minimal (delete the image if exists)
- docker build -t minimal .
- docker run -p 8080:8080 --name minimal minimal;latest
  - verify at http://localhost:8080/hello

## Install spring-boot-minimal in docker container using docker-compose
- https://docs.docker.com/compose/reference/overview/
- ./gradlew clean build
- docker rm --force minimal (deletes the container if exists)
- docker image ls -a (view list of images)
- docker rmi minimal (delete the image if exists)
- docker build -t minimal .  (build the image)
- docker-compose up -d (-d for detached mode)
  - verify at http://localhost:8080/hello
- docker-compose -f abc-compose.yml up -d (docker-compose.yml is default but another file can be used this way)
- docker-compose down (to stop and delete containers)

## Install spring-boot-minimal in Kubernetes inside Docker Desktop

### Install kubernetes dashboard in Docker Desktop (Or use OpenLens)
- https://kubernetes.io/docs/tasks/access-application-cluster/web-ui-dashboard/

### Few kubectl commands
- kubectl get all --all-namespaces
- kubectl get nodes
- kubectl get services
- kubectl get pods
- kubectl cluster-info
- kubectl get namespaces

###  Deploy a sample appllication (microbot) in kubernetes
- kubectl create deployment microbot --image=dontrebootme/microbot:v1
- kubectl scale deployment microbot --replicas=4
- kubectl expose deployment microbot --type=NodePort --port=80 --name=microbot-service
- kubectl get all --all-namespaces
- kubectl get all -n default (get all from default namespace)
  - access the service using http://<ip_address> (get ip address from above command)
    - Make it work (It currently does not work)
- kubectl delete deployment microbot

### Deploy spring-boot-minimal in kubernetes
- ./gradlew clean build
- docker login
- docker build -t sunilkeyal/minimal .
- docker push sunilkeyal/minimal  (this will push to docker hub)
- kubectl create deployment minimal --image=sunilkeyal/minimal
- kubectl scale deployment minimal --replicas=4
- kubectl expose deployment minimal --type=LoadBalancer --port=8080 --name=minimal-service
  - access the service using http://localhost:8080/hello
- kubectl get all --all-namespaces (see the service ip)
- kubectl get all -n default (get all from default namespace)
- kubectl delete deployment minimal


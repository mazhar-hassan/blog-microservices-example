# blog-microservices-example
It's a sample microservices example repository contains multiple spring boot projects

This repository contains 5 service and 1 Library project

## 0 - common-dtos
`mvn clean install`

User above command to install this lib project in local m2 directory


## 1 - post-service
http://localhost:8081

## 2 -  comment-service
http://localhost:8082

Exposes
* POST /api/v1/posts/{postId}/comments
* GET /api/v1/posts/{postId}/comments

Listens
* COMMENT_MODERATED

Publish
* COMMENT_UPDATED

## 3 - moderation-service
http://localhost:8084

Exposes
* none

Listens
* COMMENT_CREATED

Publish
* COMMENT_MODERATED


## 4 - query-service
http://localhost:8083

## 5 - event-bus
http://localhost:8085


# Dockerize Services

### Build docker image
Assumption you are in parent folder
* Build docker image
* Tag it with mic-qs
* With version 01
* Point to child directory where exist the relevant docker file

* `docker build -t blog-post-service:01 -f post-service/Dockerfile .`
* `docker build -t blog-comment-service:01 -f comment-service/Dockerfile .`
* `docker build -t blog-query-service:01 -f query-service/Dockerfile .`
* `docker build -t blog-moderation-service:01 -f moderation-service/Dockerfile .`
* `docker build -t blog-event-bus:01 -f event-bus/Dockerfile .`



### Run docker Container
* `docker run -d --name mic-post -p 8081:8081 blog-post-service:01`
* `docker run -d --name mic-comment -p 8082:8082 blog-comment-service:01`
* `docker run -d --name mic-query -p 8083:8083 blog-query-service:01`
* `docker run -d --name mic-moderation -p 8084:8084 blog-moderation-service:01`
* `docker run -d --name mic-event-bus -p 8085:8085 blog-event-bus:01`




### Useful commands
#### Show docker images
`docker images`

#### Show running containers
`docker ps`

#### Show running containers and stopped
`docker ps -a`

#### Stop container
`docker stop <container-name>`

`docker stop <container-id>`

#### Remove container
`docker rm <container-name>`

### Start a stopped container
`docker start <container-name>`

### Restart a container
`docker restart <container-name>`

### Start stopped container
`docker start <container-name>`

### Rename container
`docker rename old_name new_name`

### Test a container
We can start a test container for verification etc
`docker run -d --name container-name image_name watch "date >> /var/log/date.log"`

## K8 Deployment
### Deploy post-deployment
`kubectl apply -f infrastructure/post-deployment.yml`

#### Useful commands
* kubectl apply -f configuration-file.yml
* kubectl get deployments
* kubectl describe deployment post-depl
* kubectl get pods
* kubectl delete pod post-depl-23232323

`kubectl rollout restart deployment post-depl`

`kubectl apply -f infrastructure/post-service.yml`

* kubectl get services


|NAME            |TYPE        |CLUSTER-IP      |EXTERNAL-IP   |PORT(S)          |AGE    |
|----------------|------------|----------------|--------------|-----------------|-------|
|posts-srv       |NodePort    |10.98.51.238    |\<none>        |8081:31496/TCP   |49s    |

#### Access service through node port
http://localhost:31496/api/v1/posts


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

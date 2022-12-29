Turn root user
* sudo su root

## Install Docker
* apt-get install ca-certificates curl gnupg lsb-release
* mkdir -p /etc/apt/keyrings
* curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
* apt-get update
* apt-get install docker-ce docker-ce-cli containerd.io docker-compose-plugin

## Install Maven
* apt-get install maven

## Clone project
* git clone https://github.com/mazhar-hassan/blog-microservices-example.git
* cd blog-microservices-example

## Build & Install Common DTOs lib project
This is only required if you want to run/test any spring microservice directly by running jar instead of packiging it inside docker container or K8 pods.
* cd common-dtos
* mvn clean install

## Build Docker Images
* cd ..
* docker build -t blog-post-service:01 -f post-service/Dockerfile .
* docker build -t blog-comment-service:01 -f comment-service/Dockerfile .
* docker build -t blog-query-service:01 -f query-service/Dockerfile .
* docker build -t blog-moderation-service:01 -f moderation-service/Dockerfile .
* docker build -t blog-event-bus:01 -f event-bus/Dockerfile .

## Check docker images 
These images are created locally and not pushed to docker hub or anyother repository
`docker images`
	REPOSITORY                TAG                       IMAGE ID       CREATED          SIZE
	blog-event-bus            01                        5f74f870c0ea   8 minutes ago    477MB
	blog-moderation-service   01                        eaf7967250bb   19 minutes ago   477MB
	blog-query-service        01                        26a790066e34   5 hours ago      477MB
	blog-comment-service      01                        08378703d25a   5 hours ago      477MB
	blog-post-service         01                        f438a1b5b1c5   14 hours ago     477MB
	amazoncorretto            11                        56b5071237e5   12 days ago      448MB
	maven                     3.8.4-amazoncorretto-11   ec3a16d3dc85   9 months ago     468MB
	
	#some other as images
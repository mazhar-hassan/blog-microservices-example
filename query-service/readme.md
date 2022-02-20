# Query Service

### Build docker image
Assumption you are in parent folder
* Build docker image
* Tag it with mic-qs
* With version 01
* Point to child directory where exist the relevant docker file

`docker build -t mic-qs:01 -f query-service/Dockerfile .`

### Run docker Container
docker run -d -p 8083:8083 mic-qs:01 --name blog-query-service

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
docker start <container-name>

### Restart a container
docker restart <container-name>

### Start stopped container
docker start <container-name>

### Rename container
docker rename old_name new_name

### Test a container
We can start a test container for verification etc
`docker run -d --name container-name image_name watch "date >> /var/log/date.log"


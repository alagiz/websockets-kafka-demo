# websockets-kafka-demo

deploy to Docker Swarm cluster with:
```$xslt
 env TB_IP=172.19.234.73 docker stack deploy -c /tmp/docker_images/kafka-ws-demo/docker-compose.depl.yml wsStackk
```

authenticate with passwordless usernames:
```
white-rabbit-guest
red-rabbit-guest
blue-rabbit-guest
black-rabbit-guest
rabbit-admin #to see all jobs from all users
```

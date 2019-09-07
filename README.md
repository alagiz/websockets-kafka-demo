# websockets-kafka-demo

# design
* client-app(react) is connected to gateway through websockets
* gateway and performer communicate through messages in kafka topics
* gateway and authentication communicate directly through HTTP

___

# deployment

deploy with:
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

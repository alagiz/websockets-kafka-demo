# websockets-kafka-demo

# design
* client-app(react) is connected to gateway through websockets
* gateway and performer communicate through messages in kafka topics
* gateway and authentication communicate directly through HTTP

___

# deployment

* make sure to enable proxy_wstunnel_module (instructions are in httpd service)
* deploy to Docker Swarm cluster with:
  ```$xslt
   env HOST_IP=172.19.235.31 docker stack deploy -c docker-compose.depl.yml wsStackk
  ```

___

# usage

* run 
   ```
   docker-compose up
   ```
* visit http://localhost:3100
* authenticate with passwordless usernames:
   ```
   white-rabbit-guest
   red-rabbit-guest
   blue-rabbit-guest
   black-rabbit-guest
   rabbit-admin #to see all jobs from all users
   ```

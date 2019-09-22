# websockets-kafka-demo

[aws-deployed-version](http://3.13.90.180/ws/) \<not available at the moment, but there is this, though => http://3.13.90.180/instability \>

# design
* client-app(react) is connected to gateway through websockets
* gateway and performer communicate through messages in kafka topics
* gateway and authentication communicate directly through HTTP

___

# deployment

* make sure to enable proxy_wstunnel_module (instructions are in httpd service)
* deploy to Docker Swarm cluster with:
  ```$xslt
   env HOST_IP=3.13.90.180 docker stack deploy -c docker-compose.depl.yml wsStackk
  ```

___

# usage

* run 
   ```
   docker-compose up
   ```
* visit http://localhost:8080/ws
* authenticate with passwordless usernames:
   ```
   white-collar
   black-collar
   red-collar
   blue-collar
   godfather => to see all jobs from all collars
   ```

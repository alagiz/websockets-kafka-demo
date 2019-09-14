# ec2 amazon-linux instance preparation 

* install git 
  ```
  sudo yum git install
  ```
* install docker
  ```
  sudo yum update -y
  sudo amazon-linux-extras install docker
  sudo service docker start
  sudo usermod -a -G docker ec2-user
  ```
* install docker-compose
  ```
  curl -L https://github.com/docker/compose/releases/download/1.25.0-rc2/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
  chmod +x /usr/local/bin/docker-compose
  ```
* install apache 
  ```
  sudo yum install httpd
  ```

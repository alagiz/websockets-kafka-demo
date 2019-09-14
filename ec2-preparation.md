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
* install apache 
  ```
  sudo yum install httpd
  ```

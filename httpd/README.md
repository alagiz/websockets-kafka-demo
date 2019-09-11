# Instructions for enabling of mod_proxy_wstunnel.so:

* place mod_proxy_wstunnel.so under modules folder:
  * cp mod_proxy_wstunnel.so /usr/lib64/httpd/modules/
  * chmod 755 /usr/lib64/httpd/modules/mod_proxy_wstunnel.so
* in **/etc/httpd/conf/httpd.conf** append/uncomment the following lines:
  ```
  LoadModule proxy_module modules/mod_proxy.so
  LoadModule proxy_wstunnel_module modules/mod_proxy_wstunnel.so
  ```
* in **/etc/httpd/conf/httpd.conf** append to the bottom (replace \<host-name\> with host name or ip of the machine to be deployed to):
  ```
  ProxyPass /ws http://<host-name>:3100/
  ProxyPassReverse /ws http://<host-name>:3100/

  ProxyPass /ws-gateway-http http://<host-name>:3102/
  ProxyPassReverse /ws-gateway-http http://<host-name>:3102/

  ProxyPass /ws-gateway-ws ws://<host-name>:3102/
  ProxyPassReverse /ws-gateway-ws ws://<host-name>:3102/
  ```
* restart httpd service:
  ```
  service httpd restart
  ```

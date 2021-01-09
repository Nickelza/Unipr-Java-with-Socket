# Wine Store

# How to Run:

1. Install Docker
2. Run command "docker-compose up -d"
3. Go to http://localhost:8081/index.php
4. Credential are contained in docker-compose.yml file 
5. Create DB  using : 
``` CREATE DATABASE IF NOT EXISTS `unipr`;```
5. In the nav bar click Import and choose the DDL.zip
6. Schema and Tables will be created automatically

# Credential phpMyAdmin

Server:db
User: root 
Password: my_secret_password


#Install Maven 
http://marcoparoni.altervista.org/installare-maven-su-windows-10/?doing_wp_cron=1608234007.4205861091613769531250

# Build Maven Dependencies for run correctly project
- mvn clean package install 

# Run Server
- Go into root folder project
- mvn exec:java -pl WineStore-Manager -Dexec.mainClass=startServer

# Run JavaFX (multiple times)

- Go into root folder project
- cd ui.fx/
- mvn javafx:run
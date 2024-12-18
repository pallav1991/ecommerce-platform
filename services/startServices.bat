@echo off
REM Store the current working directory in a variable
set "currentDir=%cd%"

REM Display the current working directory
echo Current Directory: %currentDir%

REM script to start service-discovery service
cd service-discovery
start "service-discovery" .\mvnw.cmd spring-boot:run
timeout /t 5 /nobreak

cd %currentDir%

REM script to start config-server service
cd config-server
start "config-server" .\mvnw.cmd spring-boot:run
timeout /t 5 /nobreak

cd %currentDir%

REM script to start api-gateway service
cd api-gateway
start "api-gateway" .\mvnw.cmd spring-boot:run
timeout /t 10 /nobreak

cd %currentDir%

REM script to start auth-service
cd auth-service
start "auth-service" .\mvnw.cmd spring-boot:run
timeout /t 10 /nobreak

cd %currentDir%


REM script to start user-service
cd user-service
start "user-service" .\mvnw.cmd spring-boot:run
timeout /t 10 /nobreak

cd %currentDir%

REM script to start product-service
cd product-service
start "product-service" .\mvnw.cmd spring-boot:run
timeout /t 10 /nobreak

cd %currentDir%
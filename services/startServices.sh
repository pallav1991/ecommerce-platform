#!/bin/bash

# Store the current working directory in a variable
currentDir=$(pwd)

# Display the current working directory
echo "Current Directory: $currentDir"

# Start service-discovery service
cd service-discovery
./mvnw spring-boot:run &
sleep 5

cd "$currentDir"

# Start config-server service
cd config-server
./mvnw spring-boot:run &
sleep 5

cd "$currentDir"

# Start api-gateway service
cd api-gateway
./mvnw spring-boot:run &
sleep 10

cd "$currentDir"

# Start auth-service
cd auth-service
./mvnw spring-boot:run &
sleep 10

cd "$currentDir"

# Start user-service
cd user-service
./mvnw spring-boot:run &
sleep 10

cd "$currentDir"

# Start product-service
cd product-service
./mvnw spring-boot:run &
sleep 10
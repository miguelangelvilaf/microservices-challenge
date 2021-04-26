# Microservices

## The challenge

We have a microservice architecture, and your task is to develop a new microservice.

During a preliminary meeting, the CEO has given some quick notes about a new feature:

- The microservice will retrieve up to 5 random Rick and Morty characters sorted by popularity from https://rickandmortyapi.com
- Any number of characters can be excluded using the character id.
- The popularity index must be included in the response along with all the character information.
- All the requests should be stored in order to analyze which kind of queries are sent by the users.

## The proposed solution

For the development of the challenge I have chosen to use Spring Cloud Netflix thanks to the ecosystem that Spring already provides for working with microservices.
In this challenge I have used the following tools and technologies:

- Java 8
- Spring Boot 2.3
- Spring Cloud Netflix
    * Eureka Server as a microservices registry server
    * Netflix Zuul as API Gateway already integrating Ribbon and Hystrix
- Docker
- NodeJS, NPM and Vue (for the development of a small SPA to visually display the data returned by the service)
- IntelliJ IDEA 2021.1

### Some facts about the proposed solution

- For the calculation of the popularity index of each character, I have based it on the number of episodes in which each character appears, in a percentage ranging from 0 to 100%.
- Although there is a Java library to exploit the Rick and Morty API, 
  I preferred to create my own domain objects with the necessary information and avoid loading the service with too many dependencies.
- I thought about using the GraphQL version to try to optimise the API requests, but given the limited time I had to do the challenge I opted to use the API Rest version.
- I have used Lombok and MapStruct to try to speed up the development process. In the case of MapStruct, the reason for using this library and not others such as ModelMapper, 
  is that ModelMapper uses runtime reflection, and I preferred to favour performance.

### What is still pending

- Develop the storage and exploitation of requests made by users. 
  My intention was to use InfluxDB or Prometheus with Docker and use the Spring Boot Actuator metrics endpoint, 
  which is already being used in this challenge to show the health of deployed microservices.
  Right now, all requests coming into the service are logged.
- The validation of input data from the rest services is also pending, as well as documenting services with Swagger.

## Deployment process

In order to deploy the project, you must first have an IDE installed that supports Java and <a href="https://www.docker.com/" target="_blank">Docker</a> for the backend, 
<a href="https://nodejs.org/" target="_blank">Node</a> and <a href="https://cli.vuejs.org/" target="_blank">Vue Cli</a> for the frontend.

Once we are sure that we meet the requirements, the process to follow is very simple:

### Step 1: Compile and deploy microservices

From the root folder of the project we must execute the following command from the terminal:

```shell
mvn clean install
```

With this we will generate the .jar of each of the necessary modules.
Then we will have to create the Docker images corresponding to each module and raise the corresponding containers with the command:

```shell
docker-compose up -d
```

### Step 2: Test the API

To test the API, first try opening the Eureka console to check that the registration and discovery service is working properly.
In a browser open the following URL:

```shell
http://localhost:8761/
```

To test the API through the API Gateway provided through the Zuul service, open a browser and test one of the available microservices:

```shell
https://localhost:8443/rickandmorty-microservice/characters
https://localhost:8443/rickandmorty-microservice/characters/5
https://localhost:8443/rickandmorty-microservice/characters/5/125,78,247
```

To test the health of a microservice you can use the *actuator* URL:

```shell
https://localhost:8443/rickandmorty-microservice/actuator/health
```

### Step 3 (optional): Scale a service

To test the API Gateway load balancer you can try scaling one of the services deployed in Docker.
For example, to increase the number of instances of the **rickandmorty-service** service to 2, run the following command from the terminal:

```shell
docker-compose up --scale rickandmorty-service=2
```

Check in the Eureka console that the number of instances of the microservice has increased to 2.

### Step 4: Vue test page

To test the services, an SPA has been created in Vue that shows the list of characters in a table.

The first step is to install the Vue client with NPM globally. To do this, run the following command from the terminal:

```shell
npm install -g @vue/cli
```

To start the application go to the **vue-test-page** folder and execute the following commands:

```shell
cd vue-test-page
npm install
npm run serve
```

If all goes well you should be able to access the application by opening the following url:

```shell
http://localhost:8080/
```

Enjoy!!!

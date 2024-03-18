<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->


  <h3 align="center"Microservices Training</h3>
</div>


<!-- ABOUT THE PROJECT -->
## About The Project
The aim of this project was to explore the microservice architecture with a first simple example. 
An ecosystem of 8 micro services has been developed, based on Spring Boot and Spring Cloud.


<details>
  <summary>Micro services</summary>
  <ol>
    <li><a href="#auth_service">auth_service</a></li>
    <li><a href="#service_01">service_01</a></li>
    <li><a href="#msStringProvider">msStringProvider</a></li>
    <li><a href="#ms-config-server">ms-config-server</a></li>
    <li><a href="#ms-string-transformer">ms-string-transformer</a></li>
    <li><a href="#ms-naming-service">ms-naming-service</a></li>
    <li><a href="#ms-client">ms-client</a></li>
    <li><a href="#ms-api-gateway">ms-api-gateway</a></li>
  </ol>
</details>


## auth_service
(rest application)
  Simple crud application where the following topics were practised:
    - security
    - error management
    - input validation
    - auditing
    - custom annotations
    - thymeleaf
    - actuator
    - REST API versioning (custom headers, custom media type, request param, uri mapping)
    - Jackson Annotations
  Stack: Spring Boot, Spring security 6, Thymeleaf

  
## service_01
  Application where following topics are tested:
  - Spring Cloud (Rest Template, WebClient, OpenFeign)
  - Webflux


## msStringProvider
(spring cloud config client)
  Application where following topics are tested:
  - @ConfigurationProperties
  - Spring Cloud Config Client
  - Netflix Eureka Client
  - Zipkin
  - Spring Cloud Bus Actuator


## ms-config-server
(spring cloud config server)
  Application where following topics are tested:
  - Spring Cloud Config Server
  - Spring Cloud Config Monitor


## ms-string-transformer
  Application where following topics are tested:
  - Netflix Eureka Client
  - Resilience4j
  - Circuit Breaker, Rate Limiter, Bulk Head
  - Zipkin, Micrometer, OpenTelemetry

   
## ms-naming-service
(discovery)
  Application where following topics are tested:
  - Netflix Eureka Server


## ms-client
  Application where following topics are tested:
  - Eureka Client - Load Balancing


## ms-api-gateway
(gateway)
  Application where following topics are tested:
  - Spring Cloud Gateway
  - Eureka Client
  - Cloud Gateway pattern, Url Rewriting
  - global filters
  - Zipkin

A Zipkin Console was implemented in a Docker container in order to monitor requests of ms-api-gateway, msStringProvider, ms-string-transformer.


<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Built With

* Java 17
* Spring Boot 3
* Spring Security 6
* Spring Data JPA
* Spring Cloud
* Thymeleaf
* Local PostgreSQL Database

IDE: Intellij Idea
Others: Docker, Postman, Git

<p align="right">(<a href="#readme-top">back to top</a>)</p>



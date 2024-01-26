# Spring Experiment

1. Navigate into one of the directories and run `make build` to install dependencies + build project (might involve docker)
2. Run `make run`
3. Run various provided make scripts to play-test the application

Dependencies:
- maven
- gradle
- docker
- java

Docker is optional. If you don't have docker, you can run the application locally by running `make *-no-docker`. But this would require some additional dependencies and configurations inincluding specific versions of mysql (flyway-compatible)

Topics covered
- Spring core
- JDBC
- R2DBC
- WebFlux, Web filters
- Spring MVC. Servlets, interceptors, filters
- Flyway
- Containerization & orchestration (docker compose)
- Tomcat (Spring without spring boot)
- Gradle
- Maven
- ReactiveCrudRepository (ORM)
- Exception handling
- Actuators
- Validation

Topics yet to be covered
- Testing
- Security
- Deployment (pulumi)
- JPA (ORM)
- Logging
- WebClient (& RestTemplate)
- WebFlux (in depth)

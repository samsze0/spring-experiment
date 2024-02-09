# Spring Experiment

1. Navigate into one of the directories and run `make build` to install dependencies + build project (might involve docker)
2. Run `make run`
3. Run various provided make scripts to play-test the application

Dependencies:
- maven (optional; use `./mvnw`)
- gradle (optional; use `./gradlew`)
- docker
- java

Docker is optional. If you don't have docker, you can run the application locally by running `make *-no-docker`. But this would require some additional dependencies and configurations inincluding specific versions of mysql (flyway-compatible)

Topics covered
- Spring core (IoC, Spring beans, reflection)
- JDBC (sql-connector impl)
- R2DBC (sql-connector impl)
- Spring webFlux, web filters
- Spring MVC. Servlet API, interceptors, filters
- Flyway
- Containerization & orchestration (docker compose)
- Tomcat (Spring without spring boot; servlet container)
- Gradle
- Maven
- Spring data repository (ORM; JPA; `ReactiveCrudRepository`)
- Exception handling (`@ControllerAdvice`)
- Actuators
- Validation (JSR 303/380 - Java bean validation 1.0/2.0; Hibernate validation impl; spring validation API)
- Deployment (pulumi)
- Logging

Topics yet to be covered
- Testing
- Hibernate ORM configuration
- Security
- `WebClient` (& `RestTemplate`)
- Profile
- Pagination, sorting, filtering

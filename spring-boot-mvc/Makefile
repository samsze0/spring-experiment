.PHONY: build run

build:
	mvn clean install

run:
	mvn spring-boot:run

hello:
	curl http://localhost:8080/

hello-post:
	curl -X POST http://localhost:8080?name=John

create-user:
	curl -X POST -H "Content-Type: application/json" -d '{"name":"John Doe"}' http://localhost:8080/users

create-user-bad:
	curl -X POST -H "Content-Type: application/json" -d '{"name":"J"}' http://localhost:8080/users

get-user:
	curl http://localhost:8080/users/1

dev: build run
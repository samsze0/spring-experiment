include .env
export

.PHONY: build run hello hello-post create-user create-user-bad get-user health metrics info dev dev-no-docker build-no-docker run-no-docker

IMAGE_NAME=spring-boot-mvc

build:
	mvn clean install
	docker build -t $(IMAGE_NAME) .

build-no-docker:
	mvn clean install

run:
	docker compose up

run-no-docker:
	mvn spring-boot:run

dev: build run

dev-no-docker: build-no-docker run-no-docker

hello:
	curl http://localhost:8080/ -H "api-key: $(API_KEY)"

hello-bad:
	curl http://localhost:8080/

hello-post:
	curl -X POST http://localhost:8080?name=John

create-user:
	curl -X POST -H "Content-Type: application/json" -d '{"name":"John Doe"}' http://localhost:8080/users

create-user-bad:
	curl -X POST -H "Content-Type: application/json" -d '{"name":"J"}' http://localhost:8080/users

get-user:
	curl http://localhost:8080/users/1

health:
	curl http://localhost:8080/manage/health

metrics:
	curl http://localhost:8080/manage/metrics

info:
	curl http://localhost:8080/manage/info

evil:
	curl http://localhost:8080/evil
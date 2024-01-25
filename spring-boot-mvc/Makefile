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

dev: build run # TODO: bind volume so that auto-reload works

dev-no-docker: build-no-docker run-no-docker

hello:
	curl http://localhost:8080/ -H "api-key: $(API_KEY)"

hello-bad:
	curl http://localhost:8080/

hello-post:
	curl -X POST http://localhost:8080?name=John -H "api-key: $(API_KEY)"

create-user:
	curl -X POST -H "Content-Type: application/json" -d '{"name":"$(NAME)", "age": "$(AGE)"}' http://localhost:8080/users -H "api-key: $(API_KEY)"

create-user-bad:
	curl -X POST -H "Content-Type: application/json" -d '{"name":"J"}' http://localhost:8080/users -H "api-key: $(API_KEY)"

get-user:
	curl http://localhost:8080/users/$(NAME) -H "api-key: $(API_KEY)"

get-users:
	curl http://localhost:8080/users -H "api-key: $(API_KEY)"

health:
	curl http://localhost:8080/manage/health

metrics:
	curl http://localhost:8080/manage/metrics

info:
	curl http://localhost:8080/manage/info

evil:
	curl http://localhost:8080/evil -H "api-key: $(API_KEY)"
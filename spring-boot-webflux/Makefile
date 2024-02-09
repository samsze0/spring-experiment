include .env
export

.PHONY: build run hello hello-post create-user create-user-bad get-user health metrics info dev dev-no-docker build-no-docker run-no-docker mysql

IMAGE_NAME=spring-boot-webflux

mysql:
	mysql -h$(DB_HOST) -u$(DB_USER) -p$(DB_PASSWORD)

build:
	gradle bootJar
	docker build -t $(IMAGE_NAME) .

build-no-docker:
	gradle bootJar

run:
	docker compose up

run-no-docker:
	gradle bootRun

dev: build run # TODO: bind volume so that auto-reload works

dev-no-docker: build-no-docker run-no-docker

hello:
	@curl http://localhost:8080/ -H "api-key: $(API_KEY)"

hello-bad:
	@curl http://localhost:8080/

hello-post:
	@curl -X POST http://localhost:8080?name=John -H "api-key: $(API_KEY)"

create-user:
	@curl -X POST -H "Content-Type: application/json" -d '{"name":"$(NAME)", "age": "$(AGE)"}' http://localhost:8080/users -H "api-key: $(API_KEY)" -H "Accept: application/json"

create-user-with-custom-validator:
	@curl -X POST -H "Content-Type: application/json" -d '{"name":"$(NAME)", "age": "$(AGE)"}' http://localhost:8080/users-with-custom-validator -H "api-key: $(API_KEY)" -H "Accept: application/json"

create-user-bad:
	@curl -X POST -H "Content-Type: application/json" -d '{"name":"J"}' http://localhost:8080/users -H "api-key: $(API_KEY)" -H "Accept: application/json"

get-user:
	@curl http://localhost:8080/users/$(NAME) -H "api-key: $(API_KEY)"

get-users:
	@curl http://localhost:8080/users -H "api-key: $(API_KEY)"

health:
	@curl http://localhost:8080/manage/health

metrics:
	@curl http://localhost:8080/manage/metrics

info:
	@curl http://localhost:8080/manage/info

evil:
	@curl http://localhost:8080/evil -H "api-key: $(API_KEY)"
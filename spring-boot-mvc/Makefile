include .env
export

.PHONY: build run hello hello-post create-user create-user-bad get-user health metrics info dev

build:
	mvn clean install

run:
	mvn spring-boot:run

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

dev: build run
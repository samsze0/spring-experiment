.PHONY: build run

IMAGE_NAME=spring-mvc

build:
	mvn package
	docker build -t $(IMAGE_NAME) .

run:
	docker run -p 8080:8080 $(IMAGE_NAME)

curl:
	curl http://localhost:8080/

dev: build run
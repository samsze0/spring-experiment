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

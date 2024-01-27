include .env
export

.PHONY: init

STACK_NAME=spring-boot-webflux

init:
	@echo "Setting azuread initial password"
	@pulumi config set --secret $(STACK_NAME):AZUREAD-INITIAL-PASSWORD $(AZUREAD_INITIAL_PASSWORD)
	@echo "Setting api key"
	@pulumi config set --secret $(STACK_NAME):API-KEY $(API_KEY)
	@echo "Setting db username"
	@pulumi config set --secret $(STACK_NAME):DB-USER $(DB_USER)
	@echo "Setting db password"
	@pulumi config set --secret $(STACK_NAME):DB-PASSWORD $(DB_PASSWORD)

dev:
	ENV=dev pulumi up

prod:
	ENV=prod pulumi up

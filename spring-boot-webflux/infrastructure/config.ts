import * as pulumi from "@pulumi/pulumi";

const PREFIX = "samszespring";

const RESOURCE_GROUP_NAME = `${PREFIX}`;
const STORAGE_ACCOUNT_NAME = `${PREFIX}`;
const SERVICE_PLAN_NAME = `${PREFIX}`;
const APP_NAME = `${PREFIX}`;
const KEY_VAULT_NAME = `${PREFIX}`;
const DEV_1_NAME = "dev-1";
const ADMIN_1_NAME = "admin-1";
const DOMAIN_NAME = "artizon.io";
const INVITATION_NAME = `${PREFIX}`;
const DB_NAME = `${PREFIX}`;
const MYSQL_SERVER_NAME = `${PREFIX}`;
const ENV: "dev" | "prod" = process.env.ENV as any;
if (!ENV) throw new Error("ENV not provided");

const pulumiConfig = new pulumi.Config();

export {
  pulumiConfig,
  RESOURCE_GROUP_NAME,
  STORAGE_ACCOUNT_NAME,
  SERVICE_PLAN_NAME,
  APP_NAME,
  KEY_VAULT_NAME,
  DEV_1_NAME,
  ADMIN_1_NAME,
  INVITATION_NAME,
  DOMAIN_NAME,
  DB_NAME,
  MYSQL_SERVER_NAME,
  ENV,
};

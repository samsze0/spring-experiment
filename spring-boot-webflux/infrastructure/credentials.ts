import * as pulumi from "@pulumi/pulumi";
import * as keyvault from "@pulumi/azure-native/keyvault";
import { KEY_VAULT_NAME, pulumiConfig, ENV } from "./config";
import { resourceGroup } from "./resource-group";
import { admins } from "./azuread";
import { current } from "./current";
import {
  CertificatePermissions,
  SecretPermissions,
  KeyPermissions,
  StoragePermissions,
  PublicNetworkAccess,
} from "@pulumi/azure-native/keyvault";
import { Output } from "@pulumi/pulumi";

const keyVault = new keyvault.Vault(KEY_VAULT_NAME, {
  resourceGroupName: resourceGroup.name,
  properties: {
    enablePurgeProtection: true,
    enableSoftDelete: true,
    enableRbacAuthorization: false,
    publicNetworkAccess:
      ENV === "dev"
        ? PublicNetworkAccess.Enabled
        : PublicNetworkAccess.Disabled,
    sku: {
      family: keyvault.SkuFamily.A,
      name: keyvault.SkuName.Standard,
    },
    tenantId: current.tenantId,
    accessPolicies: [
      {
        tenantId: current.tenantId,
        objectId: admins.objectId,
        permissions: {
          secrets: [SecretPermissions.All],
          keys: [KeyPermissions.All],
          certificates: [CertificatePermissions.All],
          storage: [StoragePermissions.All],
        },
      },
    ],
  },
});

const declareSecret = (
  secretName: string,
): {
  secret: keyvault.Secret;
  value: Output<string>;
} => {
  const value = pulumiConfig.getSecret(secretName);
  if (!value)
    throw new Error(`Secret ${secretName} not provided in pulumi config`);

  const secret = new keyvault.Secret(secretName, {
    resourceGroupName: resourceGroup.name,
    properties: {
      value,
    },
    secretName,
    vaultName: keyVault.name,
  });

  return {
    secret,
    value,
  };
};

const apiKeySecret = declareSecret("API-KEY");
const dbUserSecret = declareSecret("DB-USER");
const dbPasswordSecret = declareSecret("DB-PASSWORD");

export { keyVault, apiKeySecret, dbUserSecret, dbPasswordSecret };

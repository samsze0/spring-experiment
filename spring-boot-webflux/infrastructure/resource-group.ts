import * as resources from "@pulumi/azure-native/resources";
import { RESOURCE_GROUP_NAME } from "./config";

const resourceGroup = new resources.ResourceGroup(RESOURCE_GROUP_NAME);

export { resourceGroup };

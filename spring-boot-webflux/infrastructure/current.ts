import * as pulumi from "@pulumi/pulumi";
import * as azuread from "@pulumi/azuread";

const current = pulumi.output(azuread.getClientConfig({}));

export { current };

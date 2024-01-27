import * as pulumi from "@pulumi/pulumi";
import {
  INVITATION_NAME,
  DEV_1_NAME,
  ADMIN_1_NAME,
  DOMAIN_NAME,
  pulumiConfig,
} from "./config";
import * as azuread from "@pulumi/azuread";

const initialPassword = pulumiConfig.requireSecret("AZUREAD-INITIAL-PASSWORD");

const dev1 = new azuread.User(DEV_1_NAME, {
  userPrincipalName: `${DEV_1_NAME}@${DOMAIN_NAME}`,
  displayName: DEV_1_NAME,
  password: initialPassword,
  forcePasswordChange: false,
});

const admin1 = new azuread.User(ADMIN_1_NAME, {
  userPrincipalName: `${ADMIN_1_NAME}@${DOMAIN_NAME}`,
  displayName: ADMIN_1_NAME,
  password: initialPassword,
  forcePasswordChange: false,
});

const devs = new azuread.Group("developers", {
  displayName: "developers",
  securityEnabled: true,
});

const admins = new azuread.Group("admins", {
  displayName: "admins",
  securityEnabled: true,
});

new azuread.GroupMember(`admins-${ADMIN_1_NAME}`, {
  groupObjectId: admins.id,
  memberObjectId: admin1.id,
});

new azuread.GroupMember(`devs-${DEV_1_NAME}`, {
  groupObjectId: devs.id,
  memberObjectId: dev1.id,
});

const MAKE_INVITATION = false;  // Invitation is more like a one-time thing, so doesn't go well with Pulumi
if (MAKE_INVITATION) {
  const meInvitation = new azuread.Invitation(INVITATION_NAME, {
    message: {
      body: "Hello there! You are invited to join my Azure tenant!",
    },
    userDisplayName: "sam-sze",
    redirectUrl: "https://artizon.io",
    userEmailAddress: "mingsum.sam@gmail.com",
  });

  // Run once user accepted the invitation
  new azuread.GroupMember(`admins-${meInvitation.userDisplayName}`, {
    groupObjectId: admins.id,
    memberObjectId: meInvitation.userId,
  });
}

export { devs, admins };

########################################################################################################################
# Remove all foreign keys information
########################################################################################################################

ALTER TABLE client_apps
DROP FOREIGN KEY client_apps_user_fk;
ALTER TABLE user_roles
DROP FOREIGN KEY user_roles_role_fk;
ALTER TABLE user_roles
DROP FOREIGN KEY user_roles_user;

########################################################################################################################
# Remove all tables
########################################################################################################################

DROP TABLE IF EXISTS client_apps;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS oauth_client_details;
DROP TABLE IF EXISTS oauth_client_token;
DROP TABLE IF EXISTS oauth_access_token;
DROP TABLE IF EXISTS oauth_refresh_token;
DROP TABLE IF EXISTS oauth_code;
DROP TABLE IF EXISTS oauth_approvals;

########################################################################################################################
# Create all tables
########################################################################################################################

CREATE TABLE client_apps (
id          BIGINT       NOT NULL AUTO_INCREMENT,
create_date TINYBLOB     NOT NULL,
description VARCHAR(255),
guid        VARCHAR(255) NOT NULL,
name        VARCHAR(255) NOT NULL,
user_id     BIGINT       NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE roles (
id   BIGINT       NOT NULL AUTO_INCREMENT,
code VARCHAR(255) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE user_roles (
user_id BIGINT NOT NULL,
role_id BIGINT NOT NULL,
PRIMARY KEY (user_id, role_id)
);

CREATE TABLE users (
id       BIGINT       NOT NULL AUTO_INCREMENT,
email    VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE oauth_client_details (
  client_id               VARCHAR(255) PRIMARY KEY,
  resource_ids            VARCHAR(255),
  client_secret           VARCHAR(255),
  scope                   VARCHAR(255),
  authorized_grant_types  VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities             VARCHAR(255),
  access_token_validity   INTEGER,
  refresh_token_validity  INTEGER,
  additional_information  VARCHAR(4096),
  autoapprove             VARCHAR(255)
);

CREATE TABLE oauth_client_token (
  token_id          VARCHAR(255),
  token             LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name         VARCHAR(255),
  client_id         VARCHAR(255)
);

CREATE TABLE oauth_access_token (
  token_id          VARCHAR(255),
  token             LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name         VARCHAR(255),
  client_id         VARCHAR(255),
  authentication    LONG VARBINARY,
  refresh_token     VARCHAR(255)
);

CREATE TABLE oauth_refresh_token (
  token_id       VARCHAR(255),
  token          LONG VARBINARY,
  authentication LONG VARBINARY
);

CREATE TABLE oauth_code (
  code           VARCHAR(255),
  authentication LONG VARBINARY
);

CREATE TABLE oauth_approvals (
  userId         VARCHAR(255),
  clientId       VARCHAR(255),
  scope          VARCHAR(255),
  status         VARCHAR(10),
  expiresAt      TIMESTAMP,
  lastModifiedAt TIMESTAMP
);

########################################################################################################################
# Add all constraints
########################################################################################################################

ALTER TABLE client_apps
ADD CONSTRAINT client_apps_unique_guid UNIQUE (guid);
ALTER TABLE roles
ADD CONSTRAINT roles_unique_code UNIQUE (code);
ALTER TABLE users
ADD CONSTRAINT users_unique_email UNIQUE (email);
ALTER TABLE client_apps
ADD CONSTRAINT client_apps_user_fk FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE user_roles
ADD CONSTRAINT user_roles_role_fk FOREIGN KEY (role_id) REFERENCES roles (id);
ALTER TABLE user_roles
ADD CONSTRAINT user_roles_user FOREIGN KEY (user_id) REFERENCES users (id);

########################################################################################################################
# OAuth 2 std tables
########################################################################################################################


-- TODO: No to ja nie wiem co to ma być...
/*DROP TABLE IF EXISTS ClientDetails;
CREATE TABLE ClientDetails (
  appId                  VARCHAR(255) PRIMARY KEY,
  resourceIds            VARCHAR(255),
  appSecret              VARCHAR(255),
  scope                  VARCHAR(255),
  grantTypes             VARCHAR(255),
  redirectUrl            VARCHAR(255),
  authorities            VARCHAR(255),
  access_token_validity  INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation  VARCHAR(4096),
  autoApproveScopes      VARCHAR(255)
);*/
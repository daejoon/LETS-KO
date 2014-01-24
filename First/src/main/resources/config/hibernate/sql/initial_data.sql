INSERT INTO TB_CODE_ROLE(NAME, REMARK) VALUES('ROLE_ADMIN'      , 'ADMINISTRATOR USER');
INSERT INTO TB_CODE_ROLE(NAME, REMARK) VALUES('ROLE_USER'       , 'GENERAL USER');
INSERT INTO TB_CODE_ROLE(NAME, REMARK) VALUES('ROLE_ANONYMOUS'  , 'ANONYMOUS USER');
INSERT INTO AD_Company (AD_Company_ID, Name, Value, Description, isActive) VALUES ( 1, 'admin_company', 'admin_company', 'default company', 'Y');
INSERT INTO AD_User (AD_User_ID, AD_Company_ID, Name, Password, Description, isActive) VALUES (1, 1, 'admin', 'admin', 'default user', 'Y');
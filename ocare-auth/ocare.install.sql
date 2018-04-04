INSERT INTO tbl_oauth_client(
    cid,client_id, client_secret, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity,
    refresh_token_validity, additional_information, autoapprove
) VALUES('01234567','ocare-client', 'ocare-secret', 'foo,read,write','password,authorization_code,refresh_token', 
    null, null, 36000, 36000, null, true
);
insert into tbl_user(user_id,username,role,password,expiry,created) values(
'100','helpdesk','HELP_DESK','$2a$10$R/QRgFB1E3cQWNq8AvjFE.FqJYl7lH.ZNWp49s5yd4qWeoGxj5Rg6',3600,now()
);

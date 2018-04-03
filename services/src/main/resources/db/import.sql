------------------------------------------------------------------------------------------------------------------------
--  Role użytkowników systemu
------------------------------------------------------------------------------------------------------------------------
-- Developer aplikacji klienckiej
INSERT INTO roles(code) VALUES ('DEVELOPER');
-- Zwykły użytkownik
INSERT INTO roles(code) VALUES ('USER');

------------------------------------------------------------------------------------------------------------------------
--  Użytkownicy systemu
------------------------------------------------------------------------------------------------------------------------

-- dev@t.t : testowo
INSERT INTO users(email, password) VALUES ('dev@t.t', '$2a$10$n6oWuD9C.oxM5uRdXJ6SOu5cURrH63F51Mvu3m6xnds.1cIb0Tuc6');
-- user@t.t : Ucze123
INSERT INTO users(email, password) VALUES ('user@t.t', '$2a$10$xcXvK6JHUwZvrRydIndjrOBAu57t88iasmnWeCfaqcxU6WOS1Wg8O');
-- wojteksz128@o2.pl : ^joRemofudo
INSERT INTO users(email, password) VALUES ('wojteksz128@o2.pl', '$2a$10$e1I46ftnWWReJQWjB9T7mu6YMP11k5dP3FRuMWs7G9SRky.icGM3m');

------------------------------------------------------------------------------------------------------------------------
--  Przypisanie ról do użytkowników
------------------------------------------------------------------------------------------------------------------------
INSERT INTO user_roles ( SELECT u.id, r.id FROM users u, roles r WHERE u.email = 'dev@t.t' );
INSERT INTO user_roles ( SELECT u.id, r.id FROM users u, roles r WHERE u.email = 'user@t.t' AND r.code = 'USER' );
INSERT INTO user_roles ( SELECT u.id, r.id FROM users u, roles r WHERE u.email = 'wojteksz128@o2.pl' AND r.code = 'USER' );

------------------------------------------------------------------------------------------------------------------------
--  Dodanie aplikacji do oauth_client_details
------------------------------------------------------------------------------------------------------------------------
INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types, autoapprove)
VALUES ('AuthService', 'devAppSecret', 'devApp', 'authorization_code', true);
------------------------------------------------------------------------------------------------------------------------
--  Role użytkowników systemu
------------------------------------------------------------------------------------------------------------------------
-- Developer aplikacji klienckiej
INSERT INTO roles (code) VALUES ('DEVELOPER');
-- Zwykły użytkownik
INSERT INTO roles (code) VALUES ('USER');

------------------------------------------------------------------------------------------------------------------------
--  Użytkownicy systemu
------------------------------------------------------------------------------------------------------------------------

-- dev@t.t : testowo
INSERT INTO users (login, password) VALUES ('dev@t.t', '$2a$10$n6oWuD9C.oxM5uRdXJ6SOu5cURrH63F51Mvu3m6xnds.1cIb0Tuc6');
-- user@t.t : Ucze123
INSERT INTO users (login, password) VALUES ('user@t.t', '$2a$10$xcXvK6JHUwZvrRydIndjrOBAu57t88iasmnWeCfaqcxU6WOS1Wg8O');
-- wojteksz128@o2.pl : ^joRemofudo
INSERT INTO users (login, password) VALUES ('wojteksz128@o2.pl', '$2a$10$e1I46ftnWWReJQWjB9T7mu6YMP11k5dP3FRuMWs7G9SRky.icGM3m');

------------------------------------------------------------------------------------------------------------------------
--  Dane o użytkownikach systemu
------------------------------------------------------------------------------------------------------------------------

INSERT INTO users_personal_data (user_id, first_name, last_name, birth_date, email, url, phone_number)
VALUES ((SELECT id
         FROM users
         WHERE login = 'dev@t.t'), 'Dev', 'Test', '2000-01-01', 'dev@t.t', null, null);
INSERT INTO users_personal_data (user_id, first_name, last_name, birth_date, email, url, phone_number)
VALUES ((SELECT id
         FROM users
         WHERE login = 'user@t.t'), 'User', 'Test', '2000-01-01', 'user@t.t', null, null);
INSERT INTO users_personal_data (user_id, first_name, last_name, birth_date, email, url, phone_number)
VALUES ((SELECT id
         FROM users
         WHERE login = 'wojteksz128@o2.pl'), 'Wojciech', 'Szczepaniak', '1994-09-15', 'wojteksz128@o2.pl', null, null);

------------------------------------------------------------------------------------------------------------------------
--  Przypisanie ról do użytkowników
------------------------------------------------------------------------------------------------------------------------
INSERT INTO user_roles (SELECT
                          u.id,
                          r.id
                        FROM users u, roles r
                        WHERE u.login = 'dev@t.t');
INSERT INTO user_roles (SELECT
                          u.id,
                          r.id
                        FROM users u, roles r
                        WHERE u.login = 'user@t.t' AND r.code = 'USER');
INSERT INTO user_roles (SELECT
                          u.id,
                          r.id
                        FROM users u, roles r
                        WHERE u.login = 'wojteksz128@o2.pl' AND r.code = 'USER');

------------------------------------------------------------------------------------------------------------------------
--  Dodanie aplikacji do oauth_client_details
------------------------------------------------------------------------------------------------------------------------
INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types, access_token_validity, refresh_token_validity, autoapprove)
VALUES ('AuthService', 'devAppSecret', 'devApp', 'authorization_code,refresh_token', 86400, 2592000, 'true');
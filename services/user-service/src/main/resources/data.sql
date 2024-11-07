
-- Sample roles table if roles are stored in a separate table
-- Uncomment this if you are using a separate roles table
-- INSERT INTO roles (id, role_name) VALUES (1, 'USER'), (2, 'ADMIN');

-- Insert users into the users table
INSERT INTO users (id, username, password) VALUES
(1, 'user1', '$2a$10$UYPkhz5TFK5eOmGMOFyMe.GjH1Un8ddCNMxdIwX/6Fg2D7fbbIV3K'), -- password: userpassword
(2, 'admin1', '$2a$10$nk1R5A59QKzKzzLf/fF99.qtF3EVUn08N9AoSK2RGNOZWhOxsmDZ6'); -- password: adminpassword

INSERT INTO roles (id, role_name) VALUES (1, 'USER'), (2, 'ADMIN');

-- Insert roles for users using user_roles join table
INSERT INTO user_roles (user_id, role_id) VALUES
(1, 1),
(2, 2);

-- Note:
-- Replace the hashed passwords with your own hashes, using BCrypt for security.
-- The above BCrypt hashes correspond to "userpassword" and "adminpassword" respectively.
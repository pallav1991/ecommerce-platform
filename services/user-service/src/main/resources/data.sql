
-- Sample roles table if roles are stored in a separate table
-- Uncomment this if you are using a separate roles table
-- INSERT INTO roles (id, role_name) VALUES (1, 'USER'), (2, 'ADMIN');

-- Insert users into the users table
INSERT INTO users (id, username, password) VALUES
(1, 'user1', '$2a$10$IwmXKmOqtIzxz05y6bZCyOW4L9El2Oi4BLeGGeIiwrhO.bQ5hMmo6'), -- password: userpassword
(2, 'admin1', '$2a$10$gxxYzEeNM3/IsINDI.PcJumGB0kTPfZkasz3hvnW8HJ7WBRqf7YYe'); -- password: adminpassword

INSERT INTO roles (id, role_name) VALUES (1, 'USER'), (2, 'ADMIN');

-- Insert roles for users using user_roles join table
INSERT INTO user_roles (user_id, role_id) VALUES
(1, 1),
(2, 2);

-- Note:
-- Replace the hashed passwords with your own hashes, using BCrypt for security.
-- The above BCrypt hashes correspond to "userpassword" and "adminpassword" respectively.
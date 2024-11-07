-- Create the users table
CREATE TABLE users (
  id INT PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL
);

-- Create the roles table
CREATE TABLE roles (
  id INT PRIMARY KEY,
  role_name VARCHAR(50) NOT NULL
);

-- Create the user_roles join table
CREATE TABLE user_roles (
  user_id INT,
  role_id INT,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (role_id) REFERENCES roles(id)
);
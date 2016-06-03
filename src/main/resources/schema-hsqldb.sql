CREATE TABLE accounts (
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  is_confirmed BOOLEAN NOT NULL,
  PRIMARY KEY (email));
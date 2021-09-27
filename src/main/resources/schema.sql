DROP TABLE IF EXISTS Usuario;

CREATE TABLE Usuario (
  id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
  user_name VARCHAR(500) NOT NULL,
  encrypted_password VARCHAR(500) NOT NULL,
  user_role VARCHAR(120) NOT NULL,
  email VARCHAR(120) NOT NULL,
  token VARCHAR(500),
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
  last_login TIMESTAMP,
  update_date TIMESTAMP,
  enabled BIT NOT NULL
);


CREATE TABLE Phone (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  user_id UUID NOT NULL,
  phone_number VARCHAR(120) NOT NULL,
  city_code VARCHAR(120) NOT NULL,
  country_code VARCHAR(120) NOT NULL
);


ALTER TABLE Phone ADD CONSTRAINT fk_Phone_Usuario FOREIGN KEY (user_id) REFERENCES Usuario (id);
ALTER TABLE Usuario ADD CONSTRAINT email_unq UNIQUE (email, enabled);
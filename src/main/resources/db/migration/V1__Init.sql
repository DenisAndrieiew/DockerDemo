DROP TABLE IF EXISTS "user";
CREATE TABLE "user" (
                              username VARCHAR (250) PRIMARY KEY,
                              first_name VARCHAR(250) NOT NULL,
                              last_name VARCHAR(250) NOT NULL
);
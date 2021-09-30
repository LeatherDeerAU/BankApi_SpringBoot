CREATE TABLE user (
    id INTEGER AUTO_INCREMENT,
    first_name VARCHAR,
    last_name VARCHAR,
    number VARCHAR UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE bank_account (
    id INTEGER AUTO_INCREMENT,
    balance INTEGER,
    user_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES user(id),
    PRIMARY KEY(id)
);

CREATE TABLE card (
    id INTEGER AUTO_INCREMENT,
    number VARCHAR UNIQUE,
    bank_account_id INTEGER,
    FOREIGN KEY (bank_account_id) REFERENCES bank_account(id),
    PRIMARY KEY (id)
);
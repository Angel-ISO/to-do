
CREATE TABLE IF NOT EXISTS user (
                                    id INT PRIMARY KEY AUTO_INCREMENT,
                                    username varchar(45),
                                    password varchar(45),
                                    email varchar(45)
);

CREATE TABLE IF NOT EXISTS Role (
                                    id INT PRIMARY KEY AUTO_INCREMENT,
                                    name varchar(45)
);


CREATE TABLE IF NOT EXISTS user_role (
                                         id_user INT,
                                         id_role INT,
                                         PRIMARY KEY (id_user, id_role),
                                         FOREIGN KEY (id_user) REFERENCES user(id),
                                         FOREIGN KEY (id_role) REFERENCES Role(id)
);

CREATE TABLE IF NOT EXISTS label (
                                        id INT PRIMARY KEY AUTO_INCREMENT,
                                        name varchar(45),
                                        description varchar(80)
);

CREATE TABLE IF NOT EXISTS task (
                                    id INT PRIMARY KEY AUTO_INCREMENT,
                                    tittle varchar(45),
                                    description varchar(45),
                                    create_date datetime,
                                    due_date datetime,
                                    complete BOOLEAN,
                                    id_user INT NOT NULL,
                                    id_label INT,
                                    FOREIGN KEY (id_user) REFERENCES user(id),
                                    FOREIGN KEY (id_label) REFERENCES label(id)
);
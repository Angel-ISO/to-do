CREATE INDEX idx_user_username ON user (username);

CREATE INDEX idx_task_user ON task (id_user);

CREATE INDEX idx_task_label ON task (id_label);

CREATE INDEX idx_task_user_label ON task (id_user, id_label);


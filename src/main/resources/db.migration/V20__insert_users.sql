INSERT INTO users (email, password, authorities, enabled)
VALUES
    ('user1', '12345', '{ROLE_ADMIN, ROLE_USER}', true),
    ('user2', '12345', '{ROLE_USER}', true);
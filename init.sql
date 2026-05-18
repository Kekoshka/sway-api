USE default_db;

CREATE TABLE IF NOT EXISTS users (
    id          VARCHAR(128) PRIMARY KEY,
    email       VARCHAR(255) NOT NULL,
    name        VARCHAR(255),
    photo_url   TEXT,
    provider    VARCHAR(20) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS teams (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    code        VARCHAR(8) NOT NULL UNIQUE,
    owner_id    VARCHAR(128) NOT NULL,
    sphere      VARCHAR(20) DEFAULT 'office',
    FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS team_members (
    id                    BIGINT AUTO_INCREMENT PRIMARY KEY,
    team_id               BIGINT NOT NULL,
    user_id               VARCHAR(128) NOT NULL,
    name                  VARCHAR(255),
    photo_url             TEXT,
    role                  VARCHAR(20) DEFAULT 'member',
    position              VARCHAR(100),
    block_games           BOOLEAN DEFAULT FALSE,
    block_social          BOOLEAN DEFAULT FALSE,
    block_entertainment   BOOLEAN DEFAULT FALSE,
    block_messengers      BOOLEAN DEFAULT FALSE,
    UNIQUE KEY uq_team_user (team_id, user_id),
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS shifts (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id         VARCHAR(128) NOT NULL,
    team_id         BIGINT NOT NULL,
    date            VARCHAR(10) NOT NULL,
    planned_start   VARCHAR(10),
    planned_end     VARCHAR(10),
    actual_start_ms BIGINT DEFAULT 0,
    break_start_ms  BIGINT DEFAULT 0,
    break_end_ms    BIGINT DEFAULT 0,
    actual_end_ms   BIGINT DEFAULT 0,
    status          VARCHAR(20) DEFAULT 'absent',
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tasks (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    team_id         BIGINT NOT NULL,
    from_user_id    VARCHAR(128),
    from_user_name  VARCHAR(255),
    to_user_id      VARCHAR(128),
    title           VARCHAR(500),
    date            VARCHAR(10),
    is_done         BOOLEAN DEFAULT FALSE,
    created_at_ms   BIGINT DEFAULT 0,
    done_at_ms      BIGINT DEFAULT 0,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE
);

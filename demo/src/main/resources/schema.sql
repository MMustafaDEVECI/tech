CREATE DATABASE IF NOT EXISTS tech;

USE tech;

CREATE TABLE IF NOT EXISTS Player (
    player_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nick VARCHAR(50) NOT NULL,
    level BIGINT NOT NULL,
    xp BIGINT NOT NULL,
    country VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Game (
    game_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_name VARCHAR(100) NOT NULL,
    team_size INT NOT NULL,
    year INT NOT NULL
);

CREATE TABLE IF NOT EXISTS `Match` (
    match_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id BIGINT NOT NULL,
    match_time DATETIME NOT NULL,
    duration BIGINT NOT NULL,
    FOREIGN KEY (game_id) REFERENCES Game(game_id)
);

CREATE TABLE IF NOT EXISTS GamePlayer (
    game_id BIGINT NOT NULL,
    player_id BIGINT NOT NULL,
    win_number BIGINT NOT NULL,
    draw_number BIGINT NOT NULL,
    lose_number BIGINT NOT NULL,
    ranks VARCHAR(50),
    first_played DATETIME,
    time_played DATETIME,
    PRIMARY KEY (game_id, player_id),
    FOREIGN KEY (game_id) REFERENCES Game(game_id),
    FOREIGN KEY (player_id) REFERENCES Player(player_id)
);

CREATE TABLE IF NOT EXISTS MatchPlayer (
    match_id BIGINT NOT NULL,
    player_id BIGINT NOT NULL,
    killes BIGINT NOT NULL,
    death BIGINT NOT NULL,
    assist BIGINT NOT NULL,
    score BIGINT NOT NULL,
    PRIMARY KEY (match_id, player_id),
    FOREIGN KEY (match_id) REFERENCES `Match`(match_id),
    FOREIGN KEY (player_id) REFERENCES Player(player_id)
);

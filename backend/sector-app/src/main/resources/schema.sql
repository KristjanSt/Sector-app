DROP TABLE IF EXISTS sectors CASCADE;
DROP TABLE IF EXISTS user_data CASCADE;
DROP TABLE IF EXISTS user_selected_sectors CASCADE;

CREATE TABLE sectors (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parent_id BIGINT,
    FOREIGN KEY (parent_id) REFERENCES sectors(id)
);

CREATE TABLE user_data (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    agreed_to_terms BOOLEAN NOT NULL
);

CREATE TABLE user_selected_sectors (
    user_data_id BIGINT NOT NULL,
    sector_id BIGINT NOT NULL,
    PRIMARY KEY (user_data_id, sector_id),
    FOREIGN KEY (user_data_id) REFERENCES user_data(id),
    FOREIGN KEY (sector_id) REFERENCES sectors(id)
);

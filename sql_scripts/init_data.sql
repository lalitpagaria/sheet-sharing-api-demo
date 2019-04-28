CREATE DATABASE demo;
USE demo;

# is_deleted is used to soft delete the entry

# Email should be unique
CREATE TABLE users (
user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
user_name VARCHAR(100) NOT NULL,
email VARCHAR(100) NOT NULL,
is_deleted TINYINT(1) NOT NULL DEFAULT 0,
created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
UNIQUE KEY unique_email (email)
) ENGINE=InnoDB;

# Assuming in org people can create file with unique name
CREATE TABLE files (
file_id BIGINT AUTO_INCREMENT PRIMARY KEY,
file_name VARCHAR(255) NOT NULL,
git_repo VARCHAR(255) NOT NULL,
is_deleted TINYINT(1) NOT NULL DEFAULT 0,
created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
UNIQUE KEY unique_name (file_name)
) ENGINE=InnoDB;

# Sheet name not reqired to be unique
# file_id connect sheet to file
CREATE TABLE sheets (
sheet_id BIGINT AUTO_INCREMENT PRIMARY KEY,
file_id BIGINT NOT NULL,
sheet_name VARCHAR(255) NOT NULL,
is_deleted TINYINT(1) NOT NULL DEFAULT 0,
created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (file_id) REFERENCES files(file_id)
) ENGINE=InnoDB;

# Read and write access of the cells in the sheet to user
CREATE TABLE access (
access_id BIGINT AUTO_INCREMENT PRIMARY KEY,
sheet_id BIGINT NOT NULL,
user_id BIGINT NOT NULL,
read_access_bitmap BIT(64) NOT NULL DEFAULT 0,
write_access_bitmap BIT(64) NOT NULL DEFAULT 0,
is_deleted TINYINT(1) NOT NULL DEFAULT 0,
created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (sheet_id) REFERENCES sheets(sheet_id),
FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE=InnoDB;


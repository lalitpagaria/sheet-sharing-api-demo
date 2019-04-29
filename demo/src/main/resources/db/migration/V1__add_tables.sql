
# is_deleted is used to soft delete the entry

# Email should be unique
CREATE TABLE users (
user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
user_name VARCHAR(100) NOT NULL,
email VARCHAR(100) NOT NULL,
is_deleted BIT(1) NOT NULL DEFAULT 0,
created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
UNIQUE KEY unique_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

# Assuming in org people can create file with unique name
CREATE TABLE files (
file_id BIGINT AUTO_INCREMENT PRIMARY KEY,
file_name VARCHAR(255) NOT NULL,
git_repo VARCHAR(255) NOT NULL,
uri_path VARCHAR(255) NOT NULL,
is_deleted BIT(1) NOT NULL DEFAULT 0,
created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
UNIQUE KEY unique_name (file_name)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

# Sheet name not reqired to be unique
# file_id connect sheet to file
CREATE TABLE sheets (
sheet_id BIGINT AUTO_INCREMENT PRIMARY KEY,
file_id BIGINT NOT NULL,
sheet_name VARCHAR(255) NOT NULL,
sheet_order INT NOT NULL,
is_deleted BIT(1) NOT NULL DEFAULT 0,
created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (file_id) REFERENCES files(file_id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

# Read and write access of the cells in the sheet to user
CREATE TABLE access (
access_id BIGINT AUTO_INCREMENT PRIMARY KEY,
sheet_id BIGINT NOT NULL,
user_id BIGINT NOT NULL,
access_matrix JSON,
is_deleted BIT(1) NOT NULL DEFAULT 0,
created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
FOREIGN KEY (sheet_id) REFERENCES sheets(sheet_id),
FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;


# Initial data
INSERT INTO users (user_id, user_name, email) VALUES (1, 'user1', 'user1@email.com');
INSERT INTO users (user_id, user_name, email) VALUES (2, 'user2', 'user2@email.com');
INSERT INTO users (user_id, user_name, email) VALUES (3, 'user3', 'user3@email.com');
INSERT INTO files (file_id, file_name, git_repo, uri_path) VALUES (1, 'demo_file', 'https://github.com/fabiocarneiro/backend-engineer-test-lalitpagaria.git', 'files/');
INSERT INTO files (file_id, file_name, git_repo, uri_path) VALUES (2, 'file1', 'https://github.com/fabiocarneiro/backend-engineer-test-lalitpagaria.git', 'files/');
INSERT INTO sheets (sheet_id, file_id, sheet_name, sheet_order) VALUES (1, 1, 'sheet1', 1);
INSERT INTO sheets (sheet_id, file_id, sheet_name, sheet_order) VALUES (2, 1, 'sheet with space', 2);
INSERT INTO sheets (sheet_id, file_id, sheet_name, sheet_order) VALUES (3, 2, 'HRReport', 1);
INSERT INTO sheets (sheet_id, file_id, sheet_name, sheet_order) VALUES (4, 2, 'Actuals', 2);
INSERT INTO sheets (sheet_id, file_id, sheet_name, sheet_order) VALUES (5, 2, 'Assumptions', 3);
INSERT INTO sheets (sheet_id, file_id, sheet_name, sheet_order) VALUES (6, 2, 'Dashboard', 4);
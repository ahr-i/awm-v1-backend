# DB TABLE

## 1. user
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| nick_name | VARCHAR(10) | | |
| rank_score | INT | | |
| image | LONGBLOB | | |
| image_hash | VARCHAR(256) | | |

## 2. user_info
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| phone_number | VARCHAR(100) | | |
| email | VARCHAR(100) | | |
| real_name | VARCHAR(10) | | |
| created_at | DATETIME | | |

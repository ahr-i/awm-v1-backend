# DB TABLE

## 1. User
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | INT | PRIMARY_KEY | NOT NULL |
| nick_name | VARCHAR(10) | | |
| rank_score | INT | | |
| image | LONGBLOB | | |
| image_hash | VARCHAR(256) | | |

## 2. User_Info
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | INT | PRIMARY_KEY | NOT NULL |
| phone_number | VARCHAR(100) | | |
| email | VARCHAR(100) | | |
| real_name | VARCHAR(10) | | |
| created_at | DATETIME | | |

## 3. User_Auth
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | INT | PRIMARY_KEY | NOT NULL |
| id | VARCHAR(100) | | |
| password | VARCHAR(100) | | |

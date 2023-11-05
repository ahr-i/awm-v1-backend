# DB TABLE

## 1. user
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| nick_name | VARCHAR(10) | | |
| rank_score | INT | | |
| image | LONGBLOB | | |
| image_hash | VARCHAR(256) | | |
| is_auth | BOOLEAN | | |

## 2. user_info
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| id | VARCHAR | PRIMARY_KEY | NOT NULL |
| phone_number | VARCHAR(100) | | |
| email | VARCHAR(100) | | |
| role | VARCHAR(100) | | |
| created_at | DATETIME | | |

## 3. user_detail
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| password | VARCHAR(100) | | |

## 4. auth_user_detail
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| provider | VARCHAR(100) | | |
| code | VARCHAR(100) | | |

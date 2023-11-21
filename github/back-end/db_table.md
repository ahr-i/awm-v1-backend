<<<<<<< HEAD
# USER TABLE

## 1. user
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| nick_name | VARCHAR(10) | | |
| rank_score | INT | | |
| image | LONGBLOB | | |
| image_hash | VARCHAR(256) | | |
| phone_number | VARCHAR(100) | | |
| email | VARCHAR(100) | | |
| state | INT | | |
| provider | VARCHAR(100) | | |
| password | VARCHAR(100) | | |
| created_at | DATETIME | | |

## 2. admin
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| password | VARCHAR(100) | | |
| role | VARCHAR(100) | | |

## 3. favorite_list
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| list | JSON | | |

## 4. item_list
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| list | JSON | | |

## 5. black_list
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| list | JSON | | |

# LOCATION TABLE

## 6. location
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| location_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| category | VARCHAR(100) | | |
| latitude | DOUBLE | | |
| longgitude | DOUBLE | | |
| score | INT | | |
| visit_count | INT | | |
| created_at | DATETIME | | |

## 7. location_info
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| info_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| user_id |  VARCHAR(100) | | |
| location_id | INT | | |
| title | VARCHAR(100) | | |
| address | VARCHAR(100) | | |
| information | VARCHAR(100) | | |
| review | VARCHAR(100) | | |
| image | LONGBLOB | | |
| image_hash | VARCHAR(256) | | |
| created_at | DATETIME | | |

## 8. location_register
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| register_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| user_id |  VARCHAR(100) | | |
| location_id | INT | | |
| category | VARCHAR(100) | | |
| created_at | DATETIME | | |

## 9. contributor
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| contributor_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| user_id |  VARCHAR(100) | | |
| location_id | INT | | |
| register_id | INT | | |
| rate | INT | | |

# COMMUNITY TABLE

## 10. post
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| post_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| location_id | INT | | |
| user_id | VARCHAR(100) | | |
| title | VARCHAR(100) | | |
| content | VARCHAR(100) | | |
| image | LONGBLOB | | |
| image_hash | VARCHAR(256) | | |
| report | INT | | |
| like | INT | | |
| created_at | DATETIME | |

## 11. comment
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| comment_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| post_id | INT | | |
| user_id | VARCHAR(100) | | |
| comment | VARCHAR(100) | | |
| report | INT | | |
| like | INT | | |
| created_at | DATETIME | |

# CHAT TABLE

## 12. chat
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| chat_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| sender | VARCHAR(100) | | |
| receiver | VARCHAR(100) | | |
| comment | VARCHAR(100) | | |
| created_at | DATETIME | | |

# SHOP TABLE

## 13. item
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| item_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| name | VARCHAR(100) | | |
| price | VARCHAR(100) | | |
=======
# USER TABLE

## 1. user
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| nick_name | VARCHAR(10) | | |
| rank_score | INT | | |
| image | LONGBLOB | | |
| image_hash | VARCHAR(256) | | |
| phone_number | VARCHAR(100) | | |
| email | VARCHAR(100) | | |
| state | INT | | |
| provider | VARCHAR(100) | | |
| password | VARCHAR(100) | | |
| created_at | DATETIME | | |

## 2. admin
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| password | VARCHAR(100) | | |
| role | VARCHAR(100) | | |

## 3. favorite_list
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| list | JSON | | |

## 4. item_list
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| list | JSON | | |

## 5. black_list
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| list | JSON | | |

# LOCATION TABLE

## 6. location
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| location_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| category | VARCHAR(100) | | |
| latitude | DOUBLE | | |
| longgitude | DOUBLE | | |
| score | INT | | |
| visit_count | INT | | |
| created_at | DATETIME | | |

## 7. location_info
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| info_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| user_id |  VARCHAR(100) | | |
| location_id | INT | | |
| title | VARCHAR(100) | | |
| address | VARCHAR(100) | | |
| information | VARCHAR(100) | | |
| review | VARCHAR(100) | | |
| image | LONGBLOB | | |
| image_hash | VARCHAR(256) | | |
| created_at | DATETIME | | |

## 8. location_register
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| register_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| user_id |  VARCHAR(100) | | |
| location_id | INT | | |
| category | VARCHAR(100) | | |
| created_at | DATETIME | | |

## 9. contributor
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| contributor_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| user_id |  VARCHAR(100) | | |
| location_id | INT | | |
| register_id | INT | | |
| rate | INT | | |

# COMMUNITY TABLE

## 10. post
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| post_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| location_id | INT | | |
| user_id | VARCHAR(100) | | |
| title | VARCHAR(100) | | |
| content | VARCHAR(100) | | |
| image | LONGBLOB | | |
| image_hash | VARCHAR(256) | | |
| report | INT | | |
| like | INT | | |
| created_at | DATETIME | |

## 11. comment
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| comment_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| post_id | INT | | |
| user_id | VARCHAR(100) | | |
| comment | VARCHAR(100) | | |
| report | INT | | |
| like | INT | | |
| created_at | DATETIME | |

# CHAT TABLE

## 12. chat
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| chat_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| sender | VARCHAR(100) | | |
| receiver | VARCHAR(100) | | |
| comment | VARCHAR(100) | | |
| created_at | DATETIME | | |

# SHOP TABLE

## 13. item
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| item_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| name | VARCHAR(100) | | |
| price | VARCHAR(100) | | |
>>>>>>> ec39bc92820df73215dd9b39b629f9db2cbb79f2
| detail | VARCHAR(100) | | |
# USER TABLE

## 1. user
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| nick_name | VARCHAR(10) | | |
| rank_score | INT | | |
| image | LONGBLOB | | |
| image_hash | VARCHAR(256) | | |
| is_auth | BOOLEAN | | |

## 2. user_info
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| phone_number | VARCHAR(100) | | |
| email | VARCHAR(100) | | |
| role | VARCHAR(100) | | |
| password | VARCHAR(100) | | |
| state | INT | | |
| created_at | DATETIME | | |

## 2.1. oauth_user_info
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| phone_number | VARCHAR(100) | | |
| email | VARCHAR(100) | | |
| role | VARCHAR(100) | | |
| state | INT | | |
| created_at | DATETIME | | |

## 3. user_detail
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| password | VARCHAR(100) | | |

## 4. auth_user_detail
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| provider | VARCHAR(100) | | |
| code | VARCHAR(100) | | |


## 5. favorite_list
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| list | JSON | | |

## 6. item_list
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| list | JSON | | |

## 7. black_list
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| user_id | VARCHAR(100) | PRIMARY_KEY | NOT NULL |
| list | JSON | | |

# LOCATION TABLE

## 8. location
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| location_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| category | VARCHAR(100) | | |
| latitude | DOUBLE | | |
| longgitude | DOUBLE | | |
| score | INT | | |
| visit_count | INT | | |
| created_at | DATETIME | | |

## 9. location_info
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

## 10. location_register
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| register_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| user_id |  VARCHAR(100) | | |
| location_id | INT | | |
| category | VARCHAR(100) | | |
| created_at | DATETIME | | |

## 11. contributor
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| contributor_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| user_id |  VARCHAR(100) | | |
| location_id | INT | | |
| register_id | INT | | |
| rate | INT | | |

# COMMUNITY TABLE

## 12. post
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

## 13. comment
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

## 14. chat
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| chat_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| sender | VARCHAR(100) | | |
| receiver | VARCHAR(100) | | |
| comment | VARCHAR(100) | | |
| created_at | DATETIME | | |

# SHOP TABLE

## 15. item
| Name | Type | Indexes | Option |
| :--- | :--- | :--- | :--- |
| item_id | INT | PRIMARY_KEY | NOT NULL, AUTO_INCREMENT |
| name | VARCHAR(100) | | |
| price | VARCHAR(100) | | |
| detail | VARCHAR(100) | | |

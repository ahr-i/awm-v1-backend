<<<<<<< HEAD
package com.example.teamproject.JpaClass.UserTable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ItemList {
    @Id
    private String userId;
    @Column(columnDefinition = "JSON")
    private String list;
}
=======
package com.example.teamproject.JpaClass.UserTable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ItemList {
    @Id
    private String userId;
    @Column(columnDefinition = "JSON")
    private String list;
}
>>>>>>> ec39bc92820df73215dd9b39b629f9db2cbb79f2

<<<<<<< HEAD
package com.example.teamproject.JpaClass.ShopTable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    @Column
    private String name;
    @Column
    private String price;
    @Column
    private String detail;
}
=======
package com.example.teamproject.JpaClass.ShopTable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    @Column
    private String name;
    @Column
    private String price;
    @Column
    private String detail;
}
>>>>>>> ec39bc92820df73215dd9b39b629f9db2cbb79f2

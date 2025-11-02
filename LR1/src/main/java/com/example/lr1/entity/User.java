package com.example.lr1.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_first_name", nullable = false, length = 100)
    private String userFirstName;

    @Column(name = "user_last_name", nullable = false, length = 100)
    private String userLastName;

    @Column(name = "user_gender")
    private Boolean userGender;

    @Column(name = "user_bday")
    @Temporal(TemporalType.DATE)
    private Date userBDay;

    @Column(name = "user_email", length = 150)
    private String userEmail;

    @Column(name = "user_phone", length = 50)
    private String userPhone;

    // Связь с лайками
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Liked> likedPositions = new ArrayList<>();

    // Конструкторы
    public User() {}

    public User(String userPassword, String userFirstName, String userLastName) {
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUserFirstName() { return userFirstName; }
    public void setUserFirstName(String userFirstName) { this.userFirstName = userFirstName; }

    public String getUserLastName() { return userLastName; }
    public void setUserLastName(String userLastName) { this.userLastName = userLastName; }
}

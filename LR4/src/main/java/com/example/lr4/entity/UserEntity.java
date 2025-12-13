package com.example.lr4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@JacksonXmlRootElement(localName = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @JacksonXmlProperty(isAttribute = true)
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
//    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<LikedEntity> likedEntityPositions = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<LikedEntity> likedEntityPositions = new ArrayList<>();


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Boolean getUserGender() {
        return userGender;
    }

    public void setUserGender(Boolean userGender) {
        this.userGender = userGender;
    }

    public Date getUserBDay() {
        return userBDay;
    }

    public void setUserBDay(Date userBDay) {
        this.userBDay = userBDay;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public List<LikedEntity> getLikedEntityPositions() {
        return likedEntityPositions;
    }

    public void setLikedEntityPositions(List<LikedEntity> likedEntityPositions) {
        this.likedEntityPositions = likedEntityPositions;
    }
}



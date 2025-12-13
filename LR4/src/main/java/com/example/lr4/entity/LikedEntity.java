package com.example.lr4.entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(
        name = "liked",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "position_id"})
)
@JacksonXmlRootElement(localName = "liked")
public class LikedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JacksonXmlProperty(isAttribute = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JacksonXmlProperty(localName = "user")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    @JacksonXmlProperty(localName = "position")
    private PositionEntity positionEntity;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "liked_time")
    @JacksonXmlProperty(localName = "likedTime")
    private Date likedTime;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public PositionEntity getPositionEntity() {
        return positionEntity;
    }

    public void setPositionEntity(PositionEntity positionEntity) {
        this.positionEntity = positionEntity;
    }

    public Date getLikedTime() {
        return likedTime;
    }

    public void setLikedTime(Date likedTime) {
        this.likedTime = likedTime;
    }
}
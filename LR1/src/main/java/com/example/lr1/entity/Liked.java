package com.example.lr1.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "liked",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "position_id"}))
public class Liked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @Column(name = "liked_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date likedTime;

    // Конструкторы
    public Liked() {}

    public Liked(User user, Position position) {
        this.user = user;
        this.position = position;
        this.likedTime = new Date();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setLikedTime(Date date) {
        this.likedTime = date;
    }

    public Long getLikedId() { return id; }
    public void setLikedId(Long likedId) { this.id = likedId; }

    public User getUser() { return user; }

    public Position getPosition() { return position; }

    public Date getLikedTime() { return likedTime; }
}

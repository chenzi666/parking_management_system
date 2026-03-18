package org.example.parking_management_system.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class User {
    private Long id;
    private String phone;
    private String password;
    private String nickname;
    private String avatar;
    private BigDecimal balance;
    private Integer membershipLevel;
    private Integer points;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public User() {}

    public User(String phone, String password, String nickname, String avatar, BigDecimal balance, 
                Integer membershipLevel, Integer points, Integer status) {
        this.phone = phone;
        this.password = password;
        this.nickname = nickname;
        this.avatar = avatar;
        this.balance = balance;
        this.membershipLevel = membershipLevel;
        this.points = points;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(Integer membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", balance=" + balance +
                ", membershipLevel=" + membershipLevel +
                ", points=" + points +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
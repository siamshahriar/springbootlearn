package com.example.demo.model;


import javax.persistence.*;

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userRoleId;

    //user
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    //role
    @ManyToOne
    private Role role;

    public UserRole() {
    }

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

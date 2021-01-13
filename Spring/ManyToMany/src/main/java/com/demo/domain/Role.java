package com.demo.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roldId;
    @Column(name = "role_name")
    private String roldName;

   /* @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "sys_user_role",
               joinColumns = {@JoinColumn(name = "sys_role_id",referencedColumnName = "role_id")},
               inverseJoinColumns = {@JoinColumn(name = "sys_user_id",referencedColumnName = "user_id")})*/
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<User>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getRoldId() {
        return roldId;
    }

    public void setRoldId(Long roldId) {
        this.roldId = roldId;
    }

    public String getRoldName() {
        return roldName;
    }

    public void setRoldName(String roldName) {
        this.roldName = roldName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roldId=" + roldId +
                ", roldName='" + roldName + '\'' +
                '}';
    }
}

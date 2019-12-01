package ru.innopolis.stc21.med.model;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import ru.innopolis.stc21.med.configs.Role;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="USERS")
public class UsersEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="username", nullable=false, length=150)
    private String username;

    @Column(name="password", nullable=false, length=100)
    private String password;

    @Transient
    private Boolean active;
    @Transient
    public List<Role> authorities;
    @Transient
    public boolean accountNonExpired;
    @Transient
    public boolean accountNonLocked;
    @Transient
    public boolean credentialsNonExpired;
    @Transient
    public boolean enabled;

    @Override
    public String toString() {
        return "id = " + this.id + " , login = " + this.username;
    }



}
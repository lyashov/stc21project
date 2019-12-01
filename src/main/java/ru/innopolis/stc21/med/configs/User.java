package ru.innopolis.stc21.med.configs;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.List;

@Slf4j
@Data
public class User implements UserDetails {
    public Long id;
    public String username;
    public List<Role> authorities;
    public String password;
    public boolean accountNonExpired;
    public boolean accountNonLocked;
    public boolean credentialsNonExpired;
    public boolean enabled;
}
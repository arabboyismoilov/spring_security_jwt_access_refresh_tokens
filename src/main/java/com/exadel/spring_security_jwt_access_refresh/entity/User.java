package com.exadel.spring_security_jwt_access_refresh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    private String name;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    private boolean enabled = true;

    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Transient
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

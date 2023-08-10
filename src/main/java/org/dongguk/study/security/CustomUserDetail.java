package org.dongguk.study.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.study.domain.User;
import org.dongguk.study.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
public class CustomUserDetail implements UserDetails {
    private Long id;
    private Collection<? extends GrantedAuthority> authorities;

    @Setter
    private Map<String, Object> attributes;

    public CustomUserDetail(Long id, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.authorities = authorities;
        this.attributes = null;
    }

    public static CustomUserDetail create(UserRepository.UserLoginForm user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        return new CustomUserDetail(
                user.getId(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return id.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

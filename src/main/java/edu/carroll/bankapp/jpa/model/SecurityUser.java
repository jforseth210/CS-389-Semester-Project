package edu.carroll.bankapp.jpa.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A wrapper around our User model that implements the methods that Spring
 * Security expects from the UserDetails interface.
 */
public class SecurityUser implements UserDetails {

    private final User user;

    /**
     * Main Constructor for generating secure users
     *
     * @param user
     */
    public SecurityUser(User user) {
        this.user = user;
    }

    /**
     * Returns a user's username
     *
     * @return username - String - user's username
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Returns a user's hashed password
     *
     * @return String - user's hashed password
     */
    @Override
    public String getPassword() {
        return user.getHashedPassword();
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }

    /**
     * Checks if the account is not expired
     *
     * @return true/false
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Check if the account is not locked
     *
     * @return true/false
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Checks if the credentials are not expired
     *
     * @return true/false
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Checks if the entity is enabled
     *
     * @return true/false
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}

package site.copi.security.users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import site.copi.users.infrastructure.model.UserModel;

import java.util.Collection;
import java.util.Collections;

public abstract class CopiUserDetails implements UserDetails {
    private final UserModel userModel;

    private CopiUserDetails(UserModel userModel) {
        this.userModel = userModel;
    }

    public static CopiUserDetails of(UserModel userModel) {
        return new CopiUserDetails(userModel) {
        };
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.userModel.getRole()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.userModel.getOAuth2Id();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
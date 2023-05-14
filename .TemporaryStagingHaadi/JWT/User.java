package info.schuwan.projectone.JWT;

import com.example.mymedia.models.MmUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class User implements UserDetails {
    private String username;
    private String password;
    private Set<? extends GrantedAuthority> authorties;

    public User(String username, String password, Set<? extends GrantedAuthority> authorties) {
        this.username = username;
        this.password = password;
        this.authorties = authorties;
    }

    public static User build(MmUser user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.addAll(Arrays.asList(new SimpleGrantedAuthority(user.getRole().name())));
        return new User(user.getUsername(), user.getHash(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorties;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

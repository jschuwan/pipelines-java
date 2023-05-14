package info.schuwan.projectone.JWT;

import com.example.mymedia.dao.MmUserRepository;
import com.example.mymedia.models.MmUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JWTUserDetailsService implements UserDetailsService {
    @Autowired
    MmUserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(JWTUserDetailsService.class);
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MmUser> user = userRepository.findByUsername(username);


        if(!user.isPresent()) {
            throw new UsernameNotFoundException("User with username " + username + " was not found");
        }

        return User.build(user.get());
    }
}

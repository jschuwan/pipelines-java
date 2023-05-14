package info.schuwan.projectone.JWT;

import com.example.mymedia.services.UserService;
import com.example.mymedia.utils.dto.LoginRequest;
import com.example.mymedia.utils.dto.LoginResponse;
import com.example.mymedia.utils.dto.RegisterRequest;
import com.example.mymedia.models.MmUser;
import com.example.mymedia.utils.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("login")
    public ResponseEntity<LoginResponse> generateToken(@RequestBody LoginRequest loginRequest) throws Exception {
        authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String token = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(false, token, null));
    }

    private void authenticate(@NonNull String username, @NonNull String password) throws Exception {
        System.out.println("Authentcating " + username);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException de) {
            System.out.println("User not active");
        } catch (BadCredentialsException be) {
            throw new Exception("Invalid credentials", be);
        }
    }

    @PostMapping("register")
    public ResponseEntity registerUser(@RequestBody RegisterRequest registerRequest) throws URISyntaxException {
        MmUser user = userService.registerUser(registerRequest);

        if(user != null) {
            return ResponseEntity.created(new URI("http://localhost:8080/users/" + user.getId())).build();
        }

        return ResponseEntity.internalServerError().build();
    }
}

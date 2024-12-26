package application.service.impl;

import application.entity.User;
import application.service.interfaces.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementation of AuthService interface.
 *
 * @see application.service.interfaces.AuthorizationService
 */
@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    /**
     * UserDetailsService bean.
     *
     * @see application.service.impl.UserDetailsServiceImpl
     */
    private final UserDetailsService userDetailsService;

    /**
     * AuthenticationManager bean.
     *
     * @see application.config.SecurityConfig
     */
    private final AuthenticationManager authenticationManager;

    /**
     * PasswordEncoder bean.
     *
     * @see application.config.SecurityConfig
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Authenticates user with provided credentials.
     *
     * @param userAuth The User containing user login credentials.
     * @return User object containing the details of the logged-in user.
     * @see application.entity.User
     */
    @Override
    public User loginUser(User userAuth) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userAuth.getEmail(),
                userAuth.getPassword()
        ));

        User user = (User) userDetailsService.loadUserByUsername(userAuth.getEmail());

        if (!passwordEncoder.matches(userAuth.getPassword(), user.getPassword())) {
            throw new RuntimeException();
        }
        return user;
    }
}
package com.songify.infrastructure.security;

import com.songify.domain.usercrud.User;
import com.songify.domain.usercrud.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Log4j2
class UserDetailsServiceImpl implements UserDetailsManager {

    public static final String DEFAULT_USER_ROLE = "ROLE_USER";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        return userRepository.findFirstByEmail(email)
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void changePassword(final String oldPassword, final String newPassword) {

    }

    @Override
    public boolean userExists(final String username) {
        return userRepository.existsByEmail(username);
    }

    @Override
    public void deleteUser(final String username) {

    }

    @Override
    public void updateUser(final UserDetails user) {

    }

    @Override
    public void createUser(final UserDetails user) {
        if (userExists(user.getUsername())) {
            log.warn("User already exists");
            throw new IllegalArgumentException("User already exists");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User createdUser = new User(
                user.getUsername(),
                encodedPassword,
                true,
                List.of(DEFAULT_USER_ROLE)
        );
        User savedUser = userRepository.save(createdUser);
        log.info("Saved user with id: " + savedUser.getId());
    }
}

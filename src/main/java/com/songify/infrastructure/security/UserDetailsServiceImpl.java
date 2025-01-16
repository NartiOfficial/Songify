package com.songify.infrastructure.security;

import com.songify.domain.usercrud.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class UserDetailsServiceImpl implements UserDetailsManager {
    private final UserRepository userRepository;

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
//        userRepository.save()
    }
}

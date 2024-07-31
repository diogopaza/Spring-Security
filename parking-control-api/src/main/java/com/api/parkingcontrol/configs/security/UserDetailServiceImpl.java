package com.api.parkingcontrol.configs.security;


import com.api.parkingcontrol.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var user = userRepository.findByUserName(userName)
                .orElseThrow( () -> new UsernameNotFoundException("Usuario não encontrado"));
        return new User(user.getUsername(), user.getPassword(), true,
                true, true, true, user.getAuthorities() );
    }
}

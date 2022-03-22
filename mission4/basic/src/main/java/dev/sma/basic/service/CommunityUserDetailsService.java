package dev.sma.basic.service;

import dev.sma.basic.jpa.entity.UserEntity;
import dev.sma.basic.jpa.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommunityUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomAutowireConfigurer.class);
    private final UserRepository userRepository;

    public CommunityUserDetailsService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity userEntity = this.userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
        }
        return new User(username, userEntity.getPassword(), new ArrayList<>());
    }
}

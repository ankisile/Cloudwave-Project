package com.cloudwave.cloudwavewebservice.service;

import com.cloudwave.cloudwavewebservice.domain.user.Role;
import com.cloudwave.cloudwavewebservice.domain.user.User;
import com.cloudwave.cloudwavewebservice.domain.user.UserRepository;
import com.cloudwave.cloudwavewebservice.dto.user.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegisterDto registerDto){
        String email = registerDto.getEmail();
        String password = registerDto.getPassword();

        User user = User.builder().email(email).password(passwordEncoder.encode(password)).build();
        userRepository.save(user);
    }

}

package com.fsy2001.artwork.service;

import com.fsy2001.artwork.exception.WebRequestException;
import com.fsy2001.artwork.model.User;
import com.fsy2001.artwork.repository.UserRepository;
import com.fsy2001.artwork.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void register(User user) {
        /* 检查用户信息 */
        if (userRepository.existsById(user.getUsername()))
            throw new WebRequestException("conflict-username");


        user.password_DEBUG = user.getPassword(); // FIXME: 密码明码
        user.setRole(UserRole.USER);
        user.setPassword(encoder.encode(user.getPassword())); // 哈希加盐
        user.setBalance(0); // 初始余额
        userRepository.save(user);
    }
}

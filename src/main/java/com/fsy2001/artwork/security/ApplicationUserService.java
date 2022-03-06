package com.fsy2001.artwork.security;

import com.fsy2001.artwork.model.User;
import com.fsy2001.artwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public ApplicationUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = repository.getUserByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException(String.format("Could not find user %s", username));
        return new ApplicationUserDetail(user);
    }
}

package com.JAVA_EVAL.JAVA.security;

import com.JAVA_EVAL.JAVA.dao.UserDao;
import com.JAVA_EVAL.JAVA.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userDao.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Email introuvable");
        }
        return new AppUserDetails(optionalUser.get());
    }
}

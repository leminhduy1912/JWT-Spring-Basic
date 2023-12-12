package com.jwt.services.jwt;
import com.jwt.entities.UserEntity;
import com.jwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailService implements UserDetailsService {
@Autowired
private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findFirstByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException(
                    "User is not exist"
            );
        }
        return new User(user.getEmail(),user.getPassword(),new ArrayList<>());
    }
}

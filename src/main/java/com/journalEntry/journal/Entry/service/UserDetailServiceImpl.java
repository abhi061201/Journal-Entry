package com.journalEntry.journal.Entry.service;

import com.journalEntry.journal.Entry.Entity.User;
import com.journalEntry.journal.Entry.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        if(user!= null){
           UserDetails userDetails= org.springframework.security.core.userdetails.User
                    .builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                   .roles(user.getRole().toArray(new String [0]))
                    .build();
           return userDetails;
        }

        throw  new UsernameNotFoundException("user not found with username "+ username);
    }
}

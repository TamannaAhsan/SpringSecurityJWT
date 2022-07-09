package com.example.springsecurityfullcourse.auth;

import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
public interface AplicationUserDAO {

    Optional<ApplicationUser> selectApplicationUserNameByUserName(String username);
}

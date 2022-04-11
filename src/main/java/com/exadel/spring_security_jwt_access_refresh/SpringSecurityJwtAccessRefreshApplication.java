package com.exadel.spring_security_jwt_access_refresh;

import com.exadel.spring_security_jwt_access_refresh.entity.Role;
import com.exadel.spring_security_jwt_access_refresh.entity.User;
import com.exadel.spring_security_jwt_access_refresh.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityJwtAccessRefreshApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtAccessRefreshApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole(new Role("ROLE_USER"));
            userService.saveRole(new Role("ROLE_MANAGER"));
            userService.saveRole(new Role( "ROLE_ADMIN"));

            userService.saveUser(new User( "Arabboy", "hero", "1234", new ArrayList<>(), true));
            userService.saveUser(new User( "Akmal", "akmal", "1234", new ArrayList<>(), true));
            userService.saveUser(new User( "Avazbek", "avazbek", "1234", new ArrayList<>(), true));

            userService.addRoleToUser("hero","ROLE_ADMIN");
            userService.addRoleToUser("akmal","ROLE_MANAGER");
            userService.addRoleToUser("avazbek","ROLE_USER");
        };
    }
}

package com.exadel.spring_security_jwt_access_refresh.service;

import com.exadel.spring_security_jwt_access_refresh.entity.Role;
import com.exadel.spring_security_jwt_access_refresh.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User saveUser(User user);
    User getUser(String username);
    Role saveRole(Role role);
    User findByUsername(String username);
    Role findByRoleName(String roleName);
    void addRoleToUser(String username, String roleName);
    void refreshToken(HttpServletRequest request, HttpServletResponse response);
}

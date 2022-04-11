package com.exadel.spring_security_jwt_access_refresh.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.exadel.spring_security_jwt_access_refresh.dto.AddRoleDto;
import com.exadel.spring_security_jwt_access_refresh.entity.Role;
import com.exadel.spring_security_jwt_access_refresh.entity.User;
import com.exadel.spring_security_jwt_access_refresh.service.UserService;
import com.exadel.spring_security_jwt_access_refresh.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/role/save")
    public ResponseEntity<?> saveRole(@RequestBody Role role){
        return ResponseEntity.ok(userService.saveRole(role));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    @PostMapping("/add/role")
    public ResponseEntity<?> addRoleToUser(@RequestBody AddRoleDto form){
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        userService.refreshToken(request, response);
    }
}

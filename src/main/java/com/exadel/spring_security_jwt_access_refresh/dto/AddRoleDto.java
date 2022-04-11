package com.exadel.spring_security_jwt_access_refresh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRoleDto{
    private String username;
    private String roleName;
}

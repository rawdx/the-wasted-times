package com.newspaper.services;

import com.newspaper.entities.Role;
import com.newspaper.entities.dtos.RoleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<RoleDto> getAllRoles();
    Role convertToRoleEntity(RoleDto role);
    RoleDto convertToRoleDto(Role role);
    boolean createRole(RoleDto roleDto);
    RoleDto getRoleByName(String name);
}

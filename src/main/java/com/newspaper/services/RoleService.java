package com.newspaper.services;

import com.newspaper.entities.Role;
import com.newspaper.entities.dtos.RoleDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing roles and related operations.
 */
@Service
public interface RoleService {
    /**
     * Retrieves all roles.
     *
     * @return A list of RoleDto representing all roles.
     */
    List<RoleDto> getAllRoles();

    /**
     * Converts a RoleDto to a Role entity.
     *
     * @param role The RoleDto to be converted.
     * @return The corresponding Role entity.
     */
    Role convertToRoleEntity(RoleDto role);

    /**
     * Converts a Role entity to a RoleDto.
     *
     * @param role The Role entity to be converted.
     * @return The corresponding RoleDto.
     */
    RoleDto convertToRoleDto(Role role);

    /**
     * Creates a new role based on the provided RoleDto.
     *
     * @param roleDto The RoleDto containing role details for creation.
     * @return True if the role creation is successful, false otherwise.
     */
    boolean createRole(RoleDto roleDto);

    /**
     * Retrieves a role by its name.
     *
     * @param name The name of the role to be retrieved.
     * @return The corresponding RoleDto or null if no role with the given name is found.
     */
    RoleDto getRoleByName(String name);
}

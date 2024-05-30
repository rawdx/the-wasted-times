package com.newspaper.services.impl;

import com.newspaper.controllers.AdministrationController;
import com.newspaper.entities.Role;
import com.newspaper.entities.dtos.RoleDto;
import com.newspaper.repositories.RoleRepository;
import com.newspaper.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> categories = roleRepository.findAll();

        return categories.stream()
                .map(this::convertToRoleDto)
                .collect(Collectors.toList());
    }

    @Override
    public Role convertToRoleEntity(RoleDto roleDto) {
        Optional<Role> optionalRole = roleRepository.findByName(roleDto.getName());
        return optionalRole.orElse(null);
    }

    @Override
    public RoleDto convertToRoleDto(Role role) {
        RoleDto roleDto = new RoleDto();

        roleDto.setId(role.getId());
        roleDto.setName(role.getName());

        return roleDto;
    }

    @Override
    public boolean createRole(RoleDto roleDto) {
        try {
            Role roleEntity = convertToRoleEntity(roleDto);

            roleRepository.save(roleEntity);

            return roleRepository.existsById(roleEntity.getId());
        } catch (Exception e) {
            logger.error("Error", e);
            return false;
        }
    }

    @Override
    public RoleDto getRoleByName(String name) {
        Optional<Role> optionalRole = roleRepository.findByName(name);

        if(optionalRole.isPresent()){
            Role role = optionalRole.get();
            return convertToRoleDto(role);
        }
        return null;
    }
}

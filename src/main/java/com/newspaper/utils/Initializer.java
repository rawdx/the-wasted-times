package com.newspaper.utils;

import com.newspaper.entities.Role;
import com.newspaper.entities.dtos.RoleDto;
import com.newspaper.entities.dtos.UserDto;
import com.newspaper.repositories.RoleRepository;
import com.newspaper.repositories.UserRepository;
import com.newspaper.services.RoleService;
import com.newspaper.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Initializer component that runs on application startup to set up initial roles and an admin user.
 */
@Component
public class Initializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final UserService userService;

    public Initializer(RoleRepository roleRepository, RoleService roleService, UserRepository userRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    /**
     * Runs the initialization logic to create roles and an admin user if they do not already exist.
     *
     * @param args Command line arguments passed to the application.
     * @throws Exception if an error occurs during initialization.
     */
    @Override
    public void run(String... args) throws Exception {

//        createRoleIfNotExist("USER");
//        createRoleIfNotExist("SUBSCRIBER");
//        createRoleIfNotExist("WRITER");
//        createRoleIfNotExist("ADMIN");

        Optional<Role> optionalRole = roleRepository.findByName("ADMIN");

        if(optionalRole.isPresent()){
            Role role = optionalRole.get();
            long adminCount = userRepository.countByRole(role);
            if(adminCount == 0){
                userService.createUser(new UserDto("admin", Encryptor.encrypt("admin14564"), null, null, null, true, roleService.getRoleByName("ADMIN")));
            }
        }
    }

    /**
     * Creates a role if it does not already exist in the database.
     *
     * @param roleName The name of the role to create.
     */
    private void createRoleIfNotExist(String roleName) {
        if (roleService.getRoleByName(roleName) == null) {
            roleService.createRole(new RoleDto(roleName));
        }
    }
}
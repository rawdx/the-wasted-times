package com.newspaper.entities.dtos;

/**
 * Data Transfer Object (DTO) representing a role.
 * This class is used to transfer category-related information between different layers of the application.
 */
public class RoleDto {

    private long id;
    private String name;

    public RoleDto() {
    }

    public RoleDto(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.newspaper.entities.dtos;

/**
 * Data Transfer Object (DTO) representing a user.
 * This class is used to transfer user-related information between different layers of the application.
 */
public class UserDto {

    private long id;
    private String email;
    private String credential;
    private String name;
    private String phoneNumber;
    private String profilePicture;
    private boolean isVerified;
    private RoleDto role;

    //Constructors

    public UserDto() {
        super();
    }

    /**
     * Constructor for creating a UserDto.
     *
     * @param email       Email address of the user.
     * @param credential  Credential (password) of the user.
     * @param name        Full name of the user.
     * @param phoneNumber Phone number of the user.
     */
    public UserDto(String email, String credential, String name, String phoneNumber, String profilePicture, boolean isVerified, RoleDto role) {
        super();
        this.email = email;
        this.credential = credential;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.isVerified = isVerified;
        this.role = role;
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCredential() {
        return credential;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }
}

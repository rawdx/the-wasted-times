package com.newspaper.entities;

/**
 * Enum representing the role of a user in the application.
 * - USER: Standard user without special privileges.
 * - SUBSCRIBER: User with a subscription.
 * - WRITER: User with writing privileges.
 * - ADMIN: Administrator with full access and control.
 */
public enum UserRole {
    USER,
    SUBSCRIBER,
    WRITER,
    ADMIN
}

package com.newspaper.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

/**
 * Entity representing a token used for verifying various actions.
 * This class is mapped to the "tokens" table in the database.
 */
@Entity
@Table(name="tokens")
public class Token {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_token", nullable = false)
    private Long id;
    
    @Column(name = "token", nullable = false, unique = true)
    private String token;
    
    @Column(name = "expiry_date", nullable = false)	
    private LocalDateTime expiryDate;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_token_user"))
    private User user;
    
    //Constructors
    
	public Token() {
		super();
	}

	// Getters and setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

package com.newspaper.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.util.List;

/**
 * Entity representing a user in the application.
 * This class is mapped to the "users" table in the database.
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user", nullable = false)
	private long id;

	@NaturalId(mutable = true)
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "credential", nullable = false)
	private String credential;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "phone_number", length = 15)
	private String phoneNumber;

	@Column(name = "profile_picture")
	private byte[] profilePicture;

	@Column(name = "is_verified")
	private boolean isVerified;

	@OneToMany(mappedBy = "user")
	private List<Article> articles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments;

	@ManyToOne()
	@JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_user_role"))
	private Role role;

	// Constructors

	public User() {
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

	public void setCredential(String credential) {
		this.credential = credential;
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

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
		if(role.getName().equals("ADMIN")){
			this.isVerified = true;
		}
	}
}

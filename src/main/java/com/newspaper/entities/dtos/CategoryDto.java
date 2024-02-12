package com.newspaper.entities.dtos;

/**
 * Data Transfer Object (DTO) representing a category.
 * This class is used to transfer category-related information between different layers of the application.
 */
public class CategoryDto {
	
	private long id;
	private String name;
	private String url;

	//Constructors

	public CategoryDto() {
	}

	/**
	 * Constructor for creating a CategoryDto with a name and URL.
	 *
	 * @param name The name of the category.
	 * @param url  The URL associated with the category.
	 */
	public CategoryDto(String name, String url) {
		this.name = name;
		this.url = url;
	}

	// Getters and setters

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

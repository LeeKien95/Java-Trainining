package main.java.org.hibernate.tutorial.domain;

import java.util.HashSet;
import java.util.Set;

public class Book {
	private Long id;
	private String title;
	private Set authors = new HashSet();
	private Set categories = new HashSet();
	
	public Long getId() {
		return id;
	}
	private void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set getAuthors() {
		return authors;
	}
	public void setAuthors(Set authors) {
		this.authors = authors;
	}
	public Set getCategories() {
		return categories;
	}
	public void setCategories(Set categories) {
		this.categories = categories;
	}
	
}

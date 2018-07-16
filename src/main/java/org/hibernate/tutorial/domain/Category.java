package main.java.org.hibernate.tutorial.domain;

import java.util.HashSet;
import java.util.Set;

public class Category {
	private String name;
	private Long id;
	private Set books = new HashSet();
	
	public Set getBooks() {
		return books;
	}
	public void setBooks(Set books) {
		this.books = books;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}

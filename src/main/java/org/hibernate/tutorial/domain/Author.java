package main.java.org.hibernate.tutorial.domain;

import java.util.HashSet;
import java.util.Set;

public class Author extends Person {
	private Set books = new HashSet();

	public Set getBooks() {
		return books;
	}

	public void setBooks(Set books) {
		this.books = books;
	}
	
}

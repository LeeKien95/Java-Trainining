package main.java.org.hibernate.tutorial.dao;

import java.util.List;

import main.java.org.hibernate.tutorial.domain.Person;

public interface PersonDAO {
	public void save(Person p);
	public List<Person> list();
}

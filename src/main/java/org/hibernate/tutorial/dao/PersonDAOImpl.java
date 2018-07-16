package main.java.org.hibernate.tutorial.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import main.java.org.hibernate.tutorial.domain.Person;
import main.java.org.hibernate.tutorial.util.HibernateUtil;

public class PersonDAOImpl implements PersonDAO {
	
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public PersonDAOImpl(SessionFactory sessionFactory) {
    	this.sessionFactory = sessionFactory;
    }
    
	@Override
	public void save(Person p) {
		Session session = this.sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.persist(p);
		tx.commit();
	}

	@Override
	public List<Person> list() {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Person> personList = session.createQuery("from Person").list();
		session.getTransaction().commit();
		return personList;
	}

}
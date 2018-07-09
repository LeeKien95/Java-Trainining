package main.java.org.hibernate.tutorial;

import org.hibernate.Session;

import java.util.*;

import main.java.org.hibernate.tutorial.domain.Event;
import main.java.org.hibernate.tutorial.domain.Person;
import main.java.org.hibernate.tutorial.util.HibernateUtil;

public class EventManager {

    public static void main(String[] args) {
        EventManager mgr = new EventManager();
//        create event
//        mgr.createAndStoreEvent("My Event", new Date());

//        add person to event
//        Long eventId = mgr.createAndStoreEvent("My Event", new Date());
//        Long personId = mgr.createAndStorePerson("Foo", "Bar", 24);
//        mgr.addPersonToEvent(personId, eventId);
//        System.out.println("Added person " + personId + " to event " + eventId);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        System.out.println(session.load(Person.class, (long)1));
        // adding to the emailAddress collection might trigger a lazy load of the collection

        session.getTransaction().commit();
//        create email and add to person
        
        
        HibernateUtil.getSessionFactory().close();
    }

    private long createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        session.save(theEvent);

        session.getTransaction().commit();
        return theEvent.getId();
    }
    
    private long createAndStorePerson(String firstname, String lastname, int age) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person p = new Person();
        p.setAge(age);
        p.setFirstname(firstname);
        p.setLastname(lastname);
        session.save(p);

        session.getTransaction().commit();
        return p.getId();
    }
    
    
    
    private void addPersonToEvent(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person) session
                .createQuery("select p from Person p left join fetch p.events where p.id = :pid")
                .setParameter("pid", personId)
                .uniqueResult(); // Eager fetch the collection so we can use it detached
        Event anEvent = (Event) session.load(Event.class, eventId);

        session.getTransaction().commit();

        // End of first unit of work

        aPerson.getEvents().add(anEvent); // aPerson (and its collection) is detached

        // Begin second unit of work

        Session session2 = HibernateUtil.getSessionFactory().getCurrentSession();
        session2.beginTransaction();
        session2.update(aPerson); // Reattachment of aPerson

        session2.getTransaction().commit();
    }
    
    private void addEmailToPerson(Long personId, String emailAddress) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person) session.load(Person.class, personId);
        // adding to the emailAddress collection might trigger a lazy load of the collection
        aPerson.getEmailAddresses().add(emailAddress);

        session.getTransaction().commit();
    }

}

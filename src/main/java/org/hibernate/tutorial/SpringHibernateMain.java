package main.java.org.hibernate.tutorial;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.java.org.hibernate.tutorial.dao.PersonDAO;
import main.java.org.hibernate.tutorial.domain.Person;


public class SpringHibernateMain {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		PersonDAO personDAO = context.getBean(PersonDAO.class);
		
		List<Person> list = personDAO.list();
		
		for(Person p : list){
			System.out.println("Person List::"+p.getLastname());
		}
		//close resources
		context.close();	
	}
}
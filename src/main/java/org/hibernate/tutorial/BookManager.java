package main.java.org.hibernate.tutorial;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import main.java.org.hibernate.tutorial.domain.Author;
import main.java.org.hibernate.tutorial.domain.Book;
import main.java.org.hibernate.tutorial.domain.Category;
import main.java.org.hibernate.tutorial.domain.Event;
import main.java.org.hibernate.tutorial.domain.Person;
import main.java.org.hibernate.tutorial.util.HibernateUtil;

import java.util.List;
import java.util.logging.Logger;


public class BookManager {
	private final static Logger log = Logger.getLogger(BookManager.class.getName());
	
	public static void main(String[] args) {
		BookManager bm = new BookManager();
		
//		log.info("" + bm.addBook("Garden of Words"));
//		log.info("" + bm.addAuthor("thomas", "harris", 50));
//		log.info("" + bm.addCategory("Drama"));
//		bm.addBookToAuthor("harris", "hannibal");
		bm.addBookToCategory("hannibal", "thriller");
	}
	
	public long addBook(String title) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Book aBook = new Book();
        aBook.setTitle(title);
        session.save(aBook);

        session.getTransaction().commit();
        return aBook.getId();
	}
	
	public long addCategory(String name) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Category aCategory = new Category();
        aCategory.setName(name);
        session.save(aCategory);

        session.getTransaction().commit();
        return aCategory.getId();
	}
	
	 public long addAuthor(String firstname, String lastname, int age) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person p = new Author();
        p.setAge(age);
        p.setFirstname(firstname);
        p.setLastname(lastname);
        session.save(p);

        session.getTransaction().commit();
        return p.getId();
    }
	 
	 public void addEmailToPerson(Long personId, String emailAddress) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person) session.load(Person.class, personId,LockMode.WRITE);
        // adding to the emailAddress collection might trigger a lazy load of the collection
        aPerson.getEmailAddresses().add(emailAddress);

        session.getTransaction().commit();
    }
	 
	 public void addBookToAuthor(String authorName, String bookName) {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		 Author author = null;
		 Book book = null;
		 
		 Query query = session.createQuery("from Author where lastname like :name ");
		 query.setParameter("name", "%" + authorName + "%");
		 List authorList = (List) query.list();
		 if(!authorList.isEmpty()) {
			 author = (Author) authorList.get(0);
		 } else {
			 author = session.load(Author.class, addAuthor("", authorName, 20));
		 }
		 
		 query = session.createQuery("from Book where title like :title");
		 query.setParameter("title", "%" + bookName + "%");
		 List bookList = (List) query.list();
		 if (!bookList.isEmpty()) {
			 book = (Book) bookList.get(0);
		 } else {
			 book = session.load(Book.class, addBook(bookName));
		 }
		 
		 if(author != null && book != null) {
			 author.getBooks().add(book);
		 }
		 
		 session.save(author);
		 session.getTransaction().commit();
	 }
	 
	 public void addBookToCategory(String bookName, String categoryName) {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		 
		 Book book = null;
		 Category category = null;
		 Criteria bookCrit = session.createCriteria(Book.class);
		 bookCrit.add(Restrictions.like("title", "%" + bookName + "%"));
		 List bookList = bookCrit.list();
		 if (!bookList.isEmpty()) {
			 book = (Book) bookList.get(0);
		 } else {
			 book = session.load(Book.class, addBook(bookName));
		 }
		 
		 Criteria categoryCrit = session.createCriteria(Category.class);
		 categoryCrit.add(Restrictions.like("name", "%" + categoryName + "%"));
		 List categoryList = categoryCrit.list();
		 if (!categoryList.isEmpty()) {
			 category = (Category) categoryList.get(0);
		 } else {
			 category = session.load(Category.class, addCategory(categoryName));
		 }
		 
		 if(book != null && category != null) {
			 book.getCategories().add(category);
		 }
		 
		 session.getTransaction().commit();
		 
	 }
}

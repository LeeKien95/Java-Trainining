package main.java.org.hibernate.tutorial.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Event {
    private Long id;

    private String title;
    private Date date;
    private Set participants = new HashSet();

    public Set getParticipants() {
		return participants;
	}

	public void setParticipants(Set participants) {
		this.participants = participants;
	}

	public void addToPerson(Person p) {
		this.getParticipants().add(p);
		p.getEvents().add(this);
	}
	
	public void remoteFromPerson(Person p) {
		this.getParticipants().remove(p);
		p.getEvents().remove(this);
	}
	
	public Event() {}

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
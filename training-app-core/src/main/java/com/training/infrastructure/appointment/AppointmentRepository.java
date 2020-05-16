package com.training.infrastructure.appointment;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AppointmentRepository {

	@PersistenceContext(unitName = "blog")
	private EntityManager em;

	public void save(Appointment appointment) {
		em.persist(appointment);
	}
	
	public Appointment getByTime(LocalDateTime time) {
		Query query = em.createQuery("Select a from Appointment a where a.startTime = :time ");
	    query.setParameter("time", time);
	    
	    List<Appointment> list = query.getResultList();
	    
	    if(list.isEmpty())
	    {
	    	return null;
	    } 
	    else {
		    return list.get(0);
		}

	};

}

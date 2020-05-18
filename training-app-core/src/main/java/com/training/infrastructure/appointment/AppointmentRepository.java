package com.training.infrastructure.appointment;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AppointmentRepository {

	@PersistenceContext(unitName = "first")
	private EntityManager em;

	public void save(Appointment appointment) {
		em.persist(appointment);
	}
	
	public void update(Appointment appointment) {
		em.merge(appointment);
	}
	
	public Appointment getByTime(LocalDateTime time) {
		Query query = em.createQuery("Select a from Appointment a where a.timeStart = :time ");
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
	
	public Appointment getByJmbg(String jmbg) {
		Query query = em.createQuery("Select a from Appointment a where a.jmbg = :jmbg ");
	    query.setParameter("jmbg", jmbg);
	    
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

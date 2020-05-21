package com.training.infrastructure.database.timeslot;

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
public class TimeslotRepository {

	@PersistenceContext(unitName = "first")
	private EntityManager em;

	public void save(Timeslot timeslot) {
		em.persist(timeslot);
	}
	
	public void update(Timeslot timeslot) {
		em.merge(timeslot);
	}
	
	public Timeslot getByTime(LocalDateTime time) {
		Query query = em.createQuery("Select t from Timeslot t where t.startTime = :time ");
	    query.setParameter("time", time);
	    
	    List<Timeslot> list = query.getResultList();
	    
	    if(list.isEmpty())
	    {
	    	return null;
	    } 
	    else {
		    return list.get(0);
		}
	};	
	
	public List<Timeslot> getByDate(LocalDateTime date) {
		Query query = em.createQuery("Select t from Timeslot t where t.startTime > :time and t.endTime<:endDay");
	    query.setParameter("time", date);
	  query.setParameter("endDay", date.plusHours(12));
	    
	    return query.getResultList();
	   
	};
	
	public Timeslot getByJmbg(String jmbg) {
		Query query = em.createQuery("Select t from Timeslot t where t.jmbg = :jmbg ");
	    query.setParameter("jmbg", jmbg);
	    
	    List<Timeslot> list = query.getResultList();
	    
	    if(list.isEmpty())
	    {
	    	return null;
	    } 
	    else {
		    return list.get(0);
		}

	};
	
	public Timeslot getByJmbgAndDate(String jmbg, LocalDateTime date) {
		Query query = em.createQuery("Select t from Timeslot t where t.jmbg = :jmbg and t.startTime > :time and t.endTime < :endDay");
	    query.setParameter("jmbg", jmbg);
	    query.setParameter("time", date.toLocalDate().atTime(8, 0));
	    query.setParameter("endDay", date.plusHours(12));
	    
	    List<Timeslot> list = query.getResultList();
	    
	    if(list.isEmpty())
	    {
	    	return null;
	    } 
	    else {
		    return list.get(0);
		}

	};

}

package core.training.app.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import core.training.app.model.Timeslot;

@Stateless
public class TimeslotManager {

	@PersistenceContext(unitName = "blog")
	private EntityManager em;

	public void save(Timeslot timeslot) {
		em.persist(timeslot);
	}

}

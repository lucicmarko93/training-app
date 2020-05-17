package com.training.infrastructure.application;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ApplicationRepository {
	
	@PersistenceContext(unitName = "second")
	private EntityManager em;

	public void save(Application application) {
		em.persist(application);
	}

}

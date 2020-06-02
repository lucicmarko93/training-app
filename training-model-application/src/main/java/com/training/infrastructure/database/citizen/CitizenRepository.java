package com.training.infrastructure.database.citizen;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CitizenRepository {
	
	@PersistenceContext(unitName = "second")
	private EntityManager em;
	
	public void save(Citizen citizen) {
		em.persist(citizen);
	}

}

package com.training.infrastructure.database.report;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ReportRepository {
	
	@PersistenceContext(unitName = "second")
	private EntityManager em;
	
	
	public void save(Report report) {
		em.persist(report);
	}

}

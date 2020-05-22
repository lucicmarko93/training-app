package com.training.infrastructure.database.application;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ApplicationRepository {

	@PersistenceContext(unitName = "second")
	private EntityManager em;

	public void save(Application application) {
		em.persist(application);
	}

	public Application getByApplicationNumber(String number) {
		Query query = em.createQuery("Select a from Application a where a.applicationNumber = :number ");
		query.setParameter("number", number);

		List<Application> list = query.getResultList();

		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}

	}

	public void update(Application application) {
		em.merge(application);

	}

	public Application findById(long id) {
		return em.find(Application.class, id);
	};

}

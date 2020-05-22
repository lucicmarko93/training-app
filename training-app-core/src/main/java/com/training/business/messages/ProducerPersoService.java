package com.training.business.messages;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

import com.training.infrastructure.database.application.Application;

@Stateless
public class ProducerPersoService {
	
	@Inject
	private JMSContext jmsContext;

	@Resource(lookup = "java:/jms/queue/PersoSQueue")
	private Queue queue;
	
	public void process(Application application) {
		jmsContext.createProducer().send(queue, application);
	}
}

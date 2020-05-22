package com.training.business.messages;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Queue;

import com.training.infrastructure.database.application.Application;

@Stateless
public class ProducerPersoService {
	
	static final String APPLICATON_ID = "app_id";
	
	@Inject
	private JMSContext jmsContext;

	@Resource(lookup = "java:/jms/queue/PersoSQueue")
	private Queue queue;
	
	public void process(Application application) {
		JMSProducer producer = jmsContext.createProducer();
		producer.setProperty(APPLICATON_ID, application.getId());
		Message message = jmsContext.createMessage();
		producer.send(queue, message);
	}
}

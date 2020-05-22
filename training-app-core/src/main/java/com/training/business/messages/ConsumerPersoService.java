package com.training.business.messages;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.training.infrastructure.database.application.Application;
import com.training.infrastructure.thirdparty.PersoServiceClient;

@MessageDriven(name = "PersoSQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/PersoSQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class ConsumerPersoService implements MessageListener {

	@Inject
	private PersoServiceClient persoServiceClient;

	@Override
	public void onMessage(Message message) {
		try {
			persoServiceClient.process(message.getBody(Application.class));
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}

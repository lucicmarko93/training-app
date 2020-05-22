package com.training.business.messages;

import java.util.Objects;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.training.business.common.exceptions.ResourceNotFoundException;
import com.training.infrastructure.database.application.Application;
import com.training.infrastructure.database.application.ApplicationRepository;
import com.training.infrastructure.thirdparty.PersoServiceClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@MessageDriven(name = "PersoSQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/PersoSQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class ConsumerPersoService implements MessageListener {

	@Inject
	private PersoServiceClient persoServiceClient;

	@Inject
	private ApplicationRepository applicationRepo;

	@Override
	public void onMessage(Message message) {
		try {
			String applicationId = message.getStringProperty(ProducerPersoService.APPLICATON_ID);
			Application application = applicationRepo.findById(Long.parseLong(applicationId));
			
			if (Objects.isNull(application)) {
				throw new ResourceNotFoundException("Application is not found!");
			}
			persoServiceClient.process(application);
		} catch (JMSException e) {
			log.error("JMS exception!", e);
		}
	}
}

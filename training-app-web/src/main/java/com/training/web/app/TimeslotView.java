package com.training.web.app;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.training.business.common.MainExceptionHandler;
import com.training.business.common.exceptions.ConflictException;
import com.training.business.common.exceptions.ServiceIsNotAvailableException;
import com.training.business.services.timeslot.TimeslotService;
import com.training.infrastructure.database.timeslot.Timeslot;

import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named(value = "timeslot")
@Getter
@Setter
public class TimeslotView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3250107440517599653L;

	private List<String> times = new ArrayList<String>();
	private LocalDate date;
	private String time;

	@Inject
	private TimeslotService timeslotService;

	public void getAll() {
		try {
			times = timeslotService.getAll(this.date).stream().map(Timeslot::getStartTime)
					.map(t -> DateTimeFormatter.ISO_DATE_TIME.format(t).replace("T", " ")).collect(Collectors.toList());

		} catch (Exception e) {
			if (MainExceptionHandler.isCause(ServiceIsNotAvailableException.class, e)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Service is not available."));
			}
			
			if (MainExceptionHandler.isCause(ConflictException.class, e)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Timeslots in past are not available!"));
			}
			
			times.clear();

		}

	}

}

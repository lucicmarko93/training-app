package com.training.web.appointment;

import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbDateFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentRequest {

	@JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime timeStart;
	private String firstName;
	private String lastName;
	private String jmbg;
}

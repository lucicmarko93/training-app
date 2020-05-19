package com.training.infrastructure.database.appointment;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import com.training.infrastructure.database.common.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment extends AbstractBaseEntity {

	private LocalDateTime timeStart;
	
	private LocalDateTime endTime;

	private String firstName;

	private String lastName;

	private String jmbg;
	
	private boolean showedUp = false;
}

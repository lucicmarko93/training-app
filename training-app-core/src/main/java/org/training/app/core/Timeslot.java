package org.training.app.core;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Timeslot {
	
	@Id
	private String id;

}

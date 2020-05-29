package com.training.infrastructure.database.timeslot;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.training.infrastructure.database.common.AbstractBaseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Timeslot extends AbstractBaseEntity {

	@NotNull
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;

	@NotNull
	@NotBlank
	private String firstName;

	@NotNull
	@NotBlank
	private String lastName;

	@NotNull
	@NotBlank
	@Size(min = 13, max = 13)
	private String jmbg;
	
	private boolean showedUp;
}

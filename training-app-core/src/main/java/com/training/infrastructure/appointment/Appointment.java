package com.training.infrastructure.appointment;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import com.training.infrastructure.common.AbstractBaseEntity;

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
public class Appointment extends AbstractBaseEntity {

	private LocalDateTime startTime;

	private String firstName;

	private String lastName;

	private String jmbg;
}

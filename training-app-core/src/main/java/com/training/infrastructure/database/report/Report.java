package com.training.infrastructure.database.report;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;

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
public class Report extends AbstractBaseEntity {
	
	private LocalDate date;
	private int numberOfTimeslots;
	private int numberOfApplications;
	private BigDecimal percentageSubmitted;

}

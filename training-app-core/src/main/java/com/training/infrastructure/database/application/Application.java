package com.training.infrastructure.database.application;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.training.infrastructure.database.citizen.Citizen;
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
public class Application extends AbstractBaseEntity {

	private String applicationNumber;

	@NotNull
	@Enumerated(EnumType.STRING)
	private DocumentType documentType;

	@NotNull
	@Enumerated(EnumType.STRING)
	private ApplicationKind applicationKind;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Priority priority;

	private String comment;

	@ManyToOne
	@JoinColumn(name = "fk_citizen")
	private Citizen citizen;

	@Enumerated(EnumType.STRING)
	private ApplicationState applicationState;
}

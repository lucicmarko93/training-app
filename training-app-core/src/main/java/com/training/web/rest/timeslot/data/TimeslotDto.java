package com.training.web.rest.timeslot.data;

import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.training.web.soap.data.LocalDateAdapter;

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
@XmlAccessorType(XmlAccessType.FIELD)
public class TimeslotDto {
	
	private String id; 

	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	@JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm")
	@NotNull
	private LocalDateTime startTime;
	
	@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
	@JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime endTime;
	
	@NotBlank
	@NotNull
	private String firstName;
	
	@NotBlank
	@NotNull
	private String lastName;

	@NotBlank
	@NotNull
	private String jmbg;
	
}

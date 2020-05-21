package com.training.web.rest.timeslot.data;

import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbDateFormat;

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
public class TimeslotDto {
	
	private String id; 

	@JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startTime;
	
	@JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime endTime;
	
	private String firstName;
	
	private String lastName;

	private String jmbg;
	
}

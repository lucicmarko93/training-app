package org.training.app.core.data;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TimeslotRequest {

	private String timeStart;
	private String firstName;
	private String lastName;
	private String jmbg;
}

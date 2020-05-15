package core.training.app.data;

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
public class TimeslotRequest {

	private String timeStart;
	private String firstName;
	private String lastName;
	private String jmbg;
}

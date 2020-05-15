package core.training.app.model;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
public class Timeslot extends AbstractBaseEntity {

	private String startTime;

	private String firstName;

	private String lastName;

	private String jmbg;
}

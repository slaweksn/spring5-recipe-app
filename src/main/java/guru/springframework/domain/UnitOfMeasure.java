package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
//import lombok.EqualsAndHashCode;

@Data
@Entity
public class UnitOfMeasure {

	//@EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    public UnitOfMeasure() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnitOfMeasure(String description) {
		super();
		this.description = description;
	}

}

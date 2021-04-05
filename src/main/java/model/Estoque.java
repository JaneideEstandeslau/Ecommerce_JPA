package model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estoque {

	@Id
	@EqualsAndHashCode.Include
	private Integer id;
	
	private Integer produtoId;
	
	private Integer quantidade;
}

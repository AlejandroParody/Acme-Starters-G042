
package acme.entities;

import javax.persistence.Entity;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InventionParts extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Mandatory
	@Valid
	//@ManyToOne(optional = false)
	private Invention			invention;

	@Mandatory
	@Valid
	//@ManyToOne(optional = false)
	private Part				part;
}

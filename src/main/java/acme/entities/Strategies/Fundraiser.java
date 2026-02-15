
package acme.entities.Strategies;

import javax.persistence.Column;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;

public class Fundraiser extends AbstractEntity {

	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Mandatory
	// @ValidHeader
	@Column
	private String				bank;

	@Mandatory
	// @ValidText
	@Column
	private String				statement;

	@Mandatory
	@Valid
	@Column
	private Boolean				agent;

}

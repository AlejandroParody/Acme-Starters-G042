
package acme.entities.sponsorships;

import javax.persistence.Column;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;

public class Sponsor extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	//Attributes -----------------------------------------------------------------------

	@Mandatory
	// @ValidText
	@Column
	private String				address;

	@Mandatory
	// @ValidHeader
	@Column
	private String				statement;

	@Mandatory
	@Valid
	@Column
	private Boolean				gold;

}

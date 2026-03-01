
package acme.entities.campaign;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.ValidNumber;
import acme.constraints.ValidHeader;
import acme.constraints.ValidText;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Milestone extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;
	@Mandatory
	@ValidHeader
	@Column
	private String				title;
	@Mandatory
	@ValidText
	@Column
	private String				achievements;
	//valid score in percentage
	@Mandatory
	@ValidNumber(min = 0)
	@Column
	private Double				effort;
	@Valid
	@Mandatory
	@Column
	private MilestoneKind		kind;

	//relations 

	@Mandatory
	@Valid
	@ManyToOne(optional = false)
	private Campaign			campaign;
}

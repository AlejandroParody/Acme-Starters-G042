
package acme.entities.strategies;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidMoment.Constraint;
import acme.client.components.validation.ValidUrl;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Strategy extends AbstractEntity {

	// Serialisation version --------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Mandatory
	//@ValidTicker
	@Column(unique = true)
	private String				ticker;

	@Mandatory
	//@ValidHeader
	@Column
	private String				name;

	@Mandatory
	//@ValidText
	@Column
	private String				description;

	@Mandatory
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date				startMoment;

	@Mandatory
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	//@Temporal(TemporalType.TIMESTAMP)
	private Date				endMoment;

	@Optional
	@ValidUrl
	@Column
	private String				moreInfo;

	// Derivated  --------------------------------------------------

	/*
	 * @Valid
	 * 
	 * @Transient
	 * public Double getMonthsActive() {
	 * 
	 * }
	 * 
	 * 
	 * @Transient
	 * public Double getExpectedPercentage() {
	 * 
	 * }
	 */

	@Mandatory
	@Valid
	@Column
	private Boolean				draftMode;

	// Relations --------------------------------------------------

	@Mandatory
	@Valid
	//@ManyToOne(optional = false)
	private Fundraiser			fundraiser;

	//@Valid
	//private List<Tactic>	tactics	= new ArrayList<>();

}

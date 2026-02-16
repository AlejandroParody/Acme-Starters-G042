
package acme.entities.Strategies;

import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.Valid;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.datatypes.Moment;
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
	//@Temporal(TemporalType.TIMESTAMP)
	private Moment				startMoment;

	@Mandatory
	@ValidMoment(constraint = Constraint.ENFORCE_FUTURE)
	//@Temporal(TemporalType.TIMESTAMP)
	private Moment				endMoment;

	@Optional
	@ValidUrl
	@Column
	private String				moreInfo;

	// Derivated  --------------------------------------------------


	@Valid
	@Transient
	public Double getMonthsActive() {

		if (this.startMoment == null || this.endMoment == null)
			return 0.0;

		long days = ChronoUnit.DAYS.between(this.startMoment.toInstant(), this.endMoment.toInstant());

		double months = days / 30.0;

		return Math.round(months * 10.0) / 10.0;
	}

	/*
	 * @Transient
	 * public Double getExpectedPercentage() {
	 * 
	 * if (this.tactics == null || this.tactics.isEmpty())
	 * return 0.0;
	 * 
	 * return this.tactics.stream().mapToDouble(Tactic::getExpectedPercentage).sum();
	 * }
	 */


	@Mandatory
	@Valid
	@Column
	private Boolean		draftMode;

	// Relations --------------------------------------------------

	@Valid
	private Fundraiser	fundraiser;

	//@Valid
	//private List<Tactic>	tactics	= new ArrayList<>();

}

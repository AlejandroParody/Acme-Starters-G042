
package acme.entities.strategies;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.basis.AbstractEntity;
import acme.client.components.validation.Mandatory;
import acme.client.components.validation.Optional;
import acme.client.components.validation.ValidMoment;
import acme.client.components.validation.ValidUrl;
import acme.constraints.ValidHeader;
import acme.constraints.ValidText;
import acme.constraints.ValidTicker;
import acme.realms.Fundraiser;
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
	@ValidTicker
	@Column(unique = true)
	private String				ticker;

	@Mandatory
	@ValidHeader
	@Column
	private String				name;

	@Mandatory
	@ValidText
	@Column
	private String				description;

	@Mandatory
	@ValidMoment
	@Temporal(TemporalType.TIMESTAMP)
	private Date				startMoment;

	@Mandatory
	@ValidMoment
	@Temporal(TemporalType.TIMESTAMP)
	private Date				endMoment;

	@Optional
	@ValidUrl
	@Column
	private String				moreInfo;

	// Derivated  --------------------------------------------------


	// @Mandatory
	// @ValidNumber(min=0)
	@Transient
	public Double getMonthsActive() {
		//Duration duration = MomentHelper.computeDuration(this.startMoment, this.endMoment);
		//return (double) duration.get(ChronoUnit.MONTHS);
		return 0.0;
	}


	@Transient
	@Autowired
	private StrategyRepository repository;


	@Transient
	public Double getExpectedPercentage() {
		Double percentage = this.repository.calculateExpectedPercentage(this.getId());
		if (percentage == null)
			percentage = 0.0;
		return percentage;
	};


	@Mandatory
	@Valid
	@Column
	private Boolean		draftMode;

	// Relations --------------------------------------------------

	@Mandatory
	@Valid
	@ManyToOne(optional = false)
	private Fundraiser	fundraiser;

}

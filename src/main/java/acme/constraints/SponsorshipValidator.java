
package acme.constraints;

import java.util.Date;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.client.helpers.MomentHelper;
import acme.entities.sponsorships.Sponsorship;
import acme.entities.sponsorships.SponsorshipRepository;

@Validator
public class SponsorshipValidator extends AbstractValidator<ValidSponsorship, Sponsorship> {

	// Internal state -------------------------------------------------------------------------------------
	@Autowired
	private SponsorshipRepository repository;


	// ConstraintValidator Interface  -------------------------------------------------------------------------------------
	@Override
	protected void initialise(final ValidSponsorship annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Sponsorship sponsorship, final ConstraintValidatorContext context) {

		assert context != null;
		boolean result = true;

		if (sponsorship == null)
			return result;

		else {

			Sponsorship inDataBase = this.repository.findSponsorshipByTicker(sponsorship.getTicker());
			boolean canStablishSameTicker = inDataBase == null || inDataBase.getId() == sponsorship.getId();

			super.state(context, canStablishSameTicker, "ticker", "acme.validation.sponsorship.duplicated-ticker.message");

			boolean isSponsorshipPublished = Boolean.FALSE.equals(sponsorship.getDraftMode());

			// DEBE TENER MAS DE UNA DONATION ASOCIADA
			if (isSponsorshipPublished) {
				int numberOfDonations = this.repository.countDonationsBySponsorshipId(sponsorship.getId());
				boolean hasDonations = numberOfDonations > 0;
				super.state(context, hasDonations, "draftMode", "acme.validation.sponsorship.no-donations.message");
			}

			// INTERVALO DEBE SER VALIDO Y FUTURO
			boolean validInterval = false;
			boolean dateInFuture = false;
			Date end = sponsorship.getEndMoment();
			Date start = sponsorship.getStartMoment();
			if (start != null && end != null) {
				var now = MomentHelper.getCurrentMoment();
				validInterval = MomentHelper.isBefore(start, end);
				dateInFuture = MomentHelper.isAfter(start, now);
			}
			super.state(context, validInterval, "endMoment", "acme.validation.sponsorship.invalid-interval.message");
			super.state(context, dateInFuture, "startMoment", "acme.validation.sponsorship.past-date.message");

			result = !super.hasErrors(context);

		}

		return result;
	}

}

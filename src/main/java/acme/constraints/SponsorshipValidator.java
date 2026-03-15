
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

		if (sponsorship != null) {
			Boolean inDraftMode = sponsorship.getDraftMode();
			if (inDraftMode == null) {
				super.state(context, false, "draftMode", "acme.validation.sponsorship.null-draft-mode.message");
				result = false;
			} else if (!inDraftMode) {
				// DEBE TENER AL MENOS UNA DONACION
				int numberOfDonations = this.repository.countDonationsBySponsorshipId(sponsorship.getId());
				boolean hasMoreThanOneDonation = numberOfDonations >= 1;
				super.state(context, hasMoreThanOneDonation, "Donation relation", "acme.validation.sponsorship.no-donations.message");
				// INTERVALO DEBE SER VALIDO 
				boolean validInterval = false;
				Date end = sponsorship.getEndMoment();
				Date start = sponsorship.getStartMoment();
				if (start != null && end != null) {
					var now = MomentHelper.getCurrentMoment();
					validInterval = MomentHelper.isAfter(start, now) && MomentHelper.isBefore(start, end);
				}
				super.state(context, validInterval, "startMoment", "acme.validation.sponsorship.invalid-interval.message");
				result = !super.hasErrors(context);
			} else {
				result = !super.hasErrors(context);
			}
		}

		return result;
	}

}

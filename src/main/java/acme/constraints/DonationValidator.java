
package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import acme.client.components.validation.AbstractValidator;
import acme.client.components.validation.Validator;
import acme.entities.sponsorships.Donation;

@Validator
public class DonationValidator extends AbstractValidator<ValidDonation, Donation> {

	// ConstraintValidator Interface  -------------------------------------------------------------------------------------
	@Override
	protected void initialise(final ValidDonation annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Donation donation, final ConstraintValidatorContext context) {

		assert context != null;
		boolean result = true;

		if (donation == null)
			return result;

		else {
			String currencyUsed = donation.getMoney().getCurrency();

			if (currencyUsed != null) {
				boolean currencyIsEuro = "EUR".equals(currencyUsed);
				super.state(context, currencyIsEuro, "money", "acme.validation.donation.not-eur.message");
			}

			result = !super.hasErrors(context);

		}

		return result;
	}

}


package acme.constraints;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.validation.AbstractValidator;
import acme.client.helpers.MomentHelper;
import acme.entities.inventions.Invention;
import acme.entities.inventions.InventionRepository;

public class InventionValidator extends AbstractValidator<ValidInvention, Invention> {

	@Autowired
	private InventionRepository repository;


	@Override
	protected void initialise(final ValidInvention annotation) {
		assert annotation != null;
	}

	@Override
	public boolean isValid(final Invention invention, final ConstraintValidatorContext context) {
		assert context != null;

		boolean result;

		{
			boolean uniqueTicker;
			Invention existing;

			existing = this.repository.findInventionByTicker(invention.getTicker());
			uniqueTicker = existing == null || existing.equals(invention);
			super.state(context, uniqueTicker, "ticker", "acme.validation.invention.uniqueticker.message");
		}

		if (invention.getStartMoment() != null)
			super.state(context, MomentHelper.isFuture(invention.getStartMoment()), "startMoment", "acme.validation.invention.startmomentinfuture.message");

		if (invention.getStartMoment() != null && invention.getEndMoment() != null)
			super.state(context, MomentHelper.isAfter(invention.getEndMoment(), invention.getStartMoment()), "endMoment", "acme.validation.invention.invalidinterval.message");

		if (invention.getDraftMode() != null && invention.getDraftMode().equals(Boolean.FALSE)) {
			boolean hasParts;

			hasParts = this.repository.countPartsByInventionId(invention.getId()) > 0;
			super.state(context, hasParts, "*", "acme.validation.invention.hasparts.message");
		}

		result = !super.hasErrors(context);
		return result;
	}
}

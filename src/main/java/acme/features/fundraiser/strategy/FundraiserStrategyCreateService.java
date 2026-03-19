
package acme.features.fundraiser.strategy;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.strategies.Strategy;
import acme.realms.Fundraiser;

@Service
public class FundraiserStrategyCreateService extends AbstractService<Fundraiser, Strategy> {

	@Autowired
	private FundraiserStrategyRepository	repository;

	private Strategy						strategy;


	@Override
	public void load() {
		Fundraiser fundraiser;

		fundraiser = (Fundraiser) super.getRequest().getPrincipal().getActiveRealm();
		this.strategy = super.newObject(Strategy.class);
		this.strategy.setDraftMode(true);
		this.strategy.setFundraiser(fundraiser);
	}

	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRealmOfType(Fundraiser.class);

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		Strategy existing;
		existing = this.repository.findStrategyByTicker(this.strategy.getTicker());
		super.state(existing == null, "ticker", "acme.validation.strategy.uniqueticker.message");

		Date now = MomentHelper.getCurrentMoment();

		if (this.strategy.getStartMoment() != null)
			super.state(this.strategy.getStartMoment().after(now), "startMoment", "acme.validation.strategy.startmomentinfuture.message");

		if (this.strategy.getEndMoment() != null)
			super.state(this.strategy.getEndMoment().after(now), "endMoment", "acme.validation.strategy.endmomentinfuture.message");

		if (this.strategy.getStartMoment() != null && this.strategy.getEndMoment() != null)
			super.state(this.strategy.getEndMoment().after(this.strategy.getStartMoment()), "endMoment", "acme.validation.strategy.invalidinterval.message");

		super.validateObject(this.strategy);
	}

	@Override
	public void execute() {
		this.repository.save(this.strategy);
	}

	@Override
	public void unbind() {
		Tuple tuple;

		tuple = super.unbindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");

		tuple.put("published", false);

		super.getResponse().addData(tuple);
	}
}

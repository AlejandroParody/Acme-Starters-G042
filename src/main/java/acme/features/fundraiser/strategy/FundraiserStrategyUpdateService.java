
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
public class FundraiserStrategyUpdateService extends AbstractService<Fundraiser, Strategy> {

	@Autowired
	private FundraiserStrategyRepository	repository;

	private Strategy						strategy;


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.strategy = this.repository.findStrategyById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.strategy != null && this.strategy.getDraftMode() && this.strategy.getFundraiser().isPrincipal();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.strategy, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		Strategy existing;
		existing = this.repository.findStrategyByTickerAndNotId(this.strategy.getTicker(), this.strategy.getId());
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

		tuple.put("published", !this.strategy.getDraftMode());

		super.getResponse().addData(tuple);
	}
}

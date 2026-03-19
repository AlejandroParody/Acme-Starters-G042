
package acme.features.fundraiser.strategy;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.services.AbstractService;
import acme.entities.strategies.Strategy;
import acme.entities.strategies.Tactic;
import acme.realms.Fundraiser;

@Service
public class FundraiserStrategyPublishService extends AbstractService<Fundraiser, Strategy> {

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
		super.validateObject(this.strategy);

		{
			boolean uniqueTicker;
			Strategy existingStrategy;
			existingStrategy = this.repository.findStrategyByTicker(this.strategy.getTicker());
			uniqueTicker = existingStrategy == null || existingStrategy.equals(this.strategy);
			super.state(uniqueTicker, "ticker", "acme.validation.strategy.uniqueticker.message");
		}

		{
			Collection<Tactic> tactics;
			boolean hasTactics;
			tactics = this.repository.findTacticsByStrategyId(this.strategy.getId());
			hasTactics = tactics != null && !tactics.isEmpty();
			super.state(hasTactics, "*", "acme.validation.strategy.hastactic.message");
		}
	}

	@Override
	public void execute() {
		this.strategy.setDraftMode(false);
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

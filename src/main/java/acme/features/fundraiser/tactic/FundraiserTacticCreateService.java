
package acme.features.fundraiser.tactic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.strategies.Strategy;
import acme.entities.strategies.Tactic;
import acme.entities.strategies.TacticKind;
import acme.realms.Fundraiser;

@Service
public class FundraiserTacticCreateService extends AbstractService<Fundraiser, Tactic> {

	@Autowired
	private FundraiserTacticRepository	repository;

	private Tactic						tactic;


	@Override
	public void load() {
		int strategyId;
		Strategy strategy;
		strategyId = super.getRequest().getData("strategyId", int.class);
		strategy = this.repository.findStrategyById(strategyId);
		this.tactic = super.newObject(Tactic.class);
		if (strategy == null)
			return;
		this.tactic.setStrategy(strategy);
	}

	@Override
	public void authorise() {
		boolean status;
		int strategyId;
		Strategy strategy;

		strategyId = super.getRequest().getData("strategyId", int.class);
		strategy = this.repository.findStrategyById(strategyId);

		status = strategy != null && strategy.getDraftMode() && strategy.getFundraiser().isPrincipal();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.tactic, "name", "notes", "expectedPercentage", "tacticKind");
	}

	@Override
	public void validate() {
		super.validateObject(this.tactic);
	}

	@Override
	public void execute() {
		this.repository.save(this.tactic);
	}

	@Override
	public void unbind() {
		Tuple tuple;
		SelectChoices choices;

		choices = SelectChoices.from(TacticKind.class, this.tactic.getTacticKind());

		tuple = super.unbindObject(this.tactic, "name", "notes", "expectedPercentage", "tacticKind");

		tuple.put("tacticKinds", choices);
		tuple.put("strategyId", this.tactic.getStrategy().getId());
		tuple.put("published", !this.tactic.getStrategy().getDraftMode());

		super.getResponse().addData(tuple);
	}
}

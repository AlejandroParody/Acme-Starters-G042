
package acme.features.any.tactic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.strategies.Strategy;
import acme.entities.strategies.Tactic;
import acme.features.any.strategy.AnyStrategyRepository;
import acme.realms.Fundraiser;

@Service
public class AnyTacticListService extends AbstractService<Fundraiser, Tactic> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyTacticRepository		repository;

	private AnyStrategyRepository	strategyRepository;

	private Collection<Tactic>				tactics;

	// AbstractService<Fundraiser, Tactic> ----------------------------------


	@Override
	public void load() {
		int strategyId;
		strategyId = super.getRequest().getData("id", int.class);
		this.tactics = this.repository.findTacticByStrategy(strategyId);
	}

	@Override
	public void authorise() {
		int strategyId;
		Fundraiser fundraiser;
		Strategy strategy;

		strategyId = super.getRequest().getData("id", int.class);
		strategy = this.strategyRepository.findStrategyById(strategyId);
		fundraiser = strategy.getFundraiser();

		boolean authorised = super.getRequest().getPrincipal().hasRealm(fundraiser);
		super.setAuthorised(authorised);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.tactics, "name", "notes", "expectedPercentage", "tacticKind");
	}

}


package acme.features.any.tactic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.principals.Any;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.strategies.Tactic;
import acme.entities.strategies.TacticKind;

@Service
public class AnyTacticShowService extends AbstractService<Any, Tactic> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyTacticRepository	repository;

	private Tactic				tactic;

	// AbstractService<Any, Tactic> ------------------------------------


	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.tactic = this.repository.findTacticById(id);
	}

	@Override
	public void authorise() {
		int id;
		Tactic tact;
		boolean status;

		id = super.getRequest().getData("id", int.class);
		tact = this.repository.findTacticById(id);

		status = tact != null && (tact.getStrategy().getFundraiser().isPrincipal() || !tact.getStrategy().getDraftMode());

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		Tuple tuple;
		SelectChoices choices;

		choices = SelectChoices.from(TacticKind.class, this.tactic.getTacticKind());

		tuple = super.unbindObject(this.tactic, "name", "notes", "expectedPercentage", "tacticKind", "strategy");
		tuple.put("kinds", choices);
		tuple.put("strategy", this.tactic.getStrategy().getName());
	}
}

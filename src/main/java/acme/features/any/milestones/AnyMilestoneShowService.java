
package acme.features.any.milestones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.principals.Any;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.entities.campaign.Milestone;
import acme.entities.campaign.MilestoneKind;

@Service
public class AnyMilestoneShowService extends AbstractService<Any, Milestone> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyMilestoneRepository	repository;

	private Milestone				milestone;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.milestone = this.repository.findMilestoneById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.milestone != null && !this.milestone.getCampaign().getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		Tuple tuple;
		SelectChoices choices;

		choices = SelectChoices.from(MilestoneKind.class, this.milestone.getKind());

		tuple = super.unbindObject(this.milestone, "title", "achievements", "effort", "kind", "campaign.ticker", "campaign.name");
		tuple.put("kinds", choices);
	}
}


package acme.features.any.milestones;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.entities.campaign.Milestone;

@Service
public class AnyMilestoneListService extends AbstractService<Any, Milestone> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyMilestoneRepository	repository;

	private Collection<Milestone>	milestones;

	// AbstractService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status;
		int campaignId;
		Campaign campaign;

		campaignId = super.getRequest().getData("campaignId", int.class);
		campaign = this.repository.findCampaignById(campaignId);

		status = campaign != null && !campaign.getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void load() {
		int campaignId;

		campaignId = super.getRequest().getData("campaignId", int.class);
		this.milestones = this.repository.findMilestonesByCampaignId(campaignId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.milestones, "title", "achievements", "effort", "kind");
	}
}

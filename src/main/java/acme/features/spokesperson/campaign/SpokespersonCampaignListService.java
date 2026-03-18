
package acme.features.spokesperson.campaign;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.realms.Spokesperson;

@Service
public class SpokespersonCampaignListService extends AbstractService<Spokesperson, Campaign> {

	@Autowired
	private SpokespersonCampaignRepository	repository;

	private Collection<Campaign>			Campaigns;


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRealmOfType(Spokesperson.class);

		super.setAuthorised(status);
	}

	@Override
	public void load() {
		int spokespersonId;

		spokespersonId = super.getRequest().getPrincipal().getActiveRealm().getId();
		this.Campaigns = this.repository.findCampaignsBySpokespersonId(spokespersonId);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.Campaigns, "ticker", "name", "startMoment", "draftMode");
	}
}

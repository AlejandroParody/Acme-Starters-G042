
package acme.features.any.campaigns;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;

@Service
public class AnyCampaignListService extends AbstractService<Any, Campaign> {

	// Internal State ---------------------------------------------------------
	@Autowired
	private AnyCampaignRepository	repository;
	private Collection<Campaign>	campaigns;

	// AbstractService Interface ----------------------------------------------


	@Override
	public void load() {
		this.campaigns = this.repository.findAllPublishedCampaigns();

	}
	@Override
	public void authorise() {
		super.setAuthorised(true);
	}
	@Override
	public void unbind() {
		super.unbindObjects(this.campaigns, "ticker", "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "draftMode", "spokesperson.identity.fullName");
	}
}

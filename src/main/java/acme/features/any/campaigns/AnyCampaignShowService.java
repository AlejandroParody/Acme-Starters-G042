
package acme.features.any.campaigns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;

@Service
public class AnyCampaignShowService extends AbstractService<Any, Campaign> {

	//Internal State---------------------------------------------
	@Autowired
	private AnyCampaignRepository	repository;
	private Campaign				campaign;


	//Abstract Service--------------------------------------------
	@Override
	public void load() {
		int id;
		id = super.getRequest().getData("id", int.class);
		this.campaign = this.repository.findCampaignById(id);

	}
	@Override
	public void authorise() {
		boolean result;

		result = this.campaign != null && !this.campaign.getDraftMode();

		super.setAuthorised(result);
	}
	@Override
	public void unbind() {
		Tuple tuple;

		tuple = super.unbindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");

		tuple.put("spokespersonId", this.campaign.getSpokesperson().getId());

		super.getResponse().addData(tuple);

	}
}


package acme.features.spokesperson.campaign;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.helpers.MomentHelper;
import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.realms.Spokesperson;

@Service
public class SpokespersonCampaignCreateService extends AbstractService<Spokesperson, Campaign> {

	@Autowired
	private SpokespersonCampaignRepository	repository;

	private Campaign						campaign;


	@Override
	public void load() {
		Spokesperson spokesperson;

		spokesperson = (Spokesperson) super.getRequest().getPrincipal().getActiveRealm();
		this.campaign = super.newObject(Campaign.class);
		this.campaign.setDraftMode(true);
		this.campaign.setSpokesperson(spokesperson);
	}

	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRealmOfType(Spokesperson.class);

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {

		Campaign existing = this.repository.findOneByTicker(this.campaign.getTicker());
		super.state(existing == null, "ticker", "acme.validation.strategy.duplicated.message");

		Date now = MomentHelper.getCurrentMoment();

		if (this.campaign.getStartMoment() != null)
			super.state(this.campaign.getStartMoment().after(now), "startMoment", "acme.validation.strategy.start-past.message");

		if (this.campaign.getEndMoment() != null)
			super.state(this.campaign.getEndMoment().after(now), "endMoment", "acme.validation.strategy.end-past.message");

		if (this.campaign.getStartMoment() != null && this.campaign.getEndMoment() != null)
			super.state(this.campaign.getEndMoment().after(this.campaign.getStartMoment()), "endMoment", "acme.validation.strategy.end-before-start.message");

		super.validateObject(this.campaign);
	}

	@Override
	public void execute() {
		this.repository.save(this.campaign);
	}

	@Override
	public void unbind() {
		Tuple tuple;

		tuple = super.unbindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");

		tuple.put("published", false);

		super.getResponse().addData(tuple);
	}
}

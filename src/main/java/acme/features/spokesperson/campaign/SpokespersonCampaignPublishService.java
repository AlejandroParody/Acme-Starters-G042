
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
public class SpokespersonCampaignPublishService extends AbstractService<Spokesperson, Campaign> {

	@Autowired
	private SpokespersonCampaignRepository	repository;

	private Campaign						campaign;


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.campaign = this.repository.findCampaignById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.campaign != null && this.campaign.getDraftMode() && this.campaign.getSpokesperson().isPrincipal();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");
	}

	@Override
	public void validate() {
		int numberOfMilestones = this.repository.countMilestonesByCampaignId(this.campaign.getId());
		super.state(numberOfMilestones > 0, "*", "acme.validation.campaign.hasmilestone.message");
		Campaign existing = this.repository.findOneByTicker(this.campaign.getTicker());
		boolean isNotDuplicate = existing == null || existing.getId() == this.campaign.getId();
		super.state(isNotDuplicate, "ticker", "acme.validation.campaign.duplicated.message");

		Date now = MomentHelper.getCurrentMoment();

		if (this.campaign.getStartMoment() != null)
			super.state(this.campaign.getStartMoment().after(now), "startMoment", "acme.validation.campaign.start-past.message");

		if (this.campaign.getEndMoment() != null)
			super.state(this.campaign.getEndMoment().after(now), "endMoment", "acme.validation.campaign.end-past.message");

		if (this.campaign.getStartMoment() != null && this.campaign.getEndMoment() != null)
			super.state(this.campaign.getEndMoment().after(this.campaign.getStartMoment()), "endMoment", "acme.validation.campaign.end-before-start.message");

		super.validateObject(this.campaign);
	}

	@Override
	public void execute() {
		this.campaign.setDraftMode(false);
		this.repository.save(this.campaign);
	}

	@Override
	public void unbind() {
		Tuple tuple;

		tuple = super.unbindObject(this.campaign, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo", "monthsActive", "effort");
		String language = super.getRequest().getLocale().getLanguage();
		boolean isPublished = !this.campaign.getDraftMode();

		String publishedLabel = "es".equalsIgnoreCase(language) ? isPublished ? "Sí" : "No" : isPublished ? "Yes" : "No";

		tuple.put("published", isPublished);
		tuple.put("publishedLabel", publishedLabel);

		super.getResponse().addData(tuple);
	}
}

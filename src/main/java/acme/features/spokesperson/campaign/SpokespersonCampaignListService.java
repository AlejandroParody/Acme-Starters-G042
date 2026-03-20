
package acme.features.spokesperson.campaign;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.services.AbstractService;
import acme.entities.campaign.Campaign;
import acme.realms.Spokesperson;

@Service
public class SpokespersonCampaignListService extends AbstractService<Spokesperson, Campaign> {

	@Autowired
	private SpokespersonCampaignRepository	repository;

	private Collection<Campaign>			campaigns;


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
		this.campaigns = this.repository.findCampaignsBySpokespersonId(spokespersonId);
	}

	@Override
	public void unbind() {
		for (final Campaign campaign : this.campaigns) {
			Tuple tuple;

			tuple = super.unbindObject(campaign, "ticker", "name", "startMoment", "draftMode");

			// Localise draftMode as Yes/No (or Sí/No for Spanish)
			String language = super.getRequest().getLocale().getLanguage();
			Boolean draft = campaign.getDraftMode();
			String draftLabel;
			if ("es".equalsIgnoreCase(language))
				draftLabel = Boolean.TRUE.equals(draft) ? "Sí" : "No";
			else
				draftLabel = Boolean.TRUE.equals(draft) ? "Yes" : "No";
			tuple.put("draftMode", draftLabel);
			super.getResponse().addData(tuple);
		}
	}
}

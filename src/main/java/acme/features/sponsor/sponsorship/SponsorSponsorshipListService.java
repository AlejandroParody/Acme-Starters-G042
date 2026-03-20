
package acme.features.sponsor.sponsorship;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.services.AbstractService;
import acme.entities.sponsorships.Sponsorship;
import acme.realms.Sponsor;

@Service
public class SponsorSponsorshipListService extends AbstractService<Sponsor, Sponsorship> {

	@Autowired
	private SponsorSponsorshipRepository	repository;

	private Collection<Sponsorship>			sponsorships;


	@Override
	public void authorise() {
		boolean status;

		status = super.getRequest().getPrincipal().hasRealmOfType(Sponsor.class);

		super.setAuthorised(status);
	}

	@Override
	public void load() {
		int sponsorId;

		sponsorId = super.getRequest().getPrincipal().getActiveRealm().getId();
		this.sponsorships = this.repository.findSponsorshipBySponsorId(sponsorId);
	}

	@Override
	public void unbind() {
		//for internalization
		for (final Sponsorship sponsorship : this.sponsorships) {
			Tuple tuple;

			tuple = super.unbindObject(sponsorship, "ticker", "name", "startMoment", "draftMode");

			String language = super.getRequest().getLocale().getLanguage();
			Boolean draft = sponsorship.getDraftMode();
			String draftTag;

			if ("es".equalsIgnoreCase(language))
				draftTag = Boolean.TRUE.equals(draft) ? "Si" : "No";
			else
				draftTag = Boolean.TRUE.equals(draft) ? "Yes" : "No";

			tuple.put("draftMode", draftTag);
			super.getResponse().addData(tuple);
		}

	}
}

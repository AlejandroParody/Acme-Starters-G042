
package acme.features.sponsor.donation;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.sponsorships.Donation;
import acme.entities.sponsorships.Sponsorship;
import acme.realms.Sponsor;

@Service
public class SponsorDonationListService extends AbstractService<Sponsor, Donation> {

	@Autowired
	private SponsorDonationRepository	repository;

	private Collection<Donation>		donations;


	@Override
	public void authorise() {
		int sponsorshipId;
		Sponsorship sponsorship;

		sponsorshipId = super.getRequest().getData("sponsorshipId", int.class);
		sponsorship = this.repository.findSponsorshipById(sponsorshipId);

		super.setAuthorised(sponsorship != null && sponsorship.getSponsor().isPrincipal());
	}

	@Override
	public void load() {
		int sponsorshipId;
		Sponsorship sponsorship;

		sponsorshipId = super.getRequest().getData("sponsorshipId", int.class);
		sponsorship = this.repository.findSponsorshipById(sponsorshipId);
		this.donations = this.repository.findDonationsBySponsorshipId(sponsorshipId);

		if (sponsorship != null) {
			super.getResponse().addGlobal("sponsorshipId", sponsorshipId);
			super.getResponse().addGlobal("published", !sponsorship.getDraftMode());
		}
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.donations, "name", "kind", "money");
	}
}

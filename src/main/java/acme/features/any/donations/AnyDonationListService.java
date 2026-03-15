
package acme.features.any.donations;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.principals.Any;
import acme.client.services.AbstractService;
import acme.entities.sponsorships.Donation;
import acme.entities.sponsorships.Sponsorship;
import acme.features.any.sponsorships.AnySponsorshipRepository;

@Service
public class AnyDonationListService extends AbstractService<Any, Donation> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnyDonationRepository		donationRepository;

	@Autowired
	private AnySponsorshipRepository	sponsorshipRepository;

	private Collection<Donation>		donations;

	// AbstractService interface -------------------------------------------


	@Override
	public void load() {
		int sponsorshipId;

		sponsorshipId = super.getRequest().getData("sponsorshipId", int.class);
		this.donations = this.donationRepository.findDonationsBySponsorshipId(sponsorshipId);
	}

	@Override
	public void authorise() {
		boolean status;
		int sponsorshipId;
		Sponsorship sponsorship;

		sponsorshipId = super.getRequest().getData("sponsorshipId", int.class);
		sponsorship = this.sponsorshipRepository.findSponsorshipById(sponsorshipId);

		status = sponsorship != null && !sponsorship.getDraftMode();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.donations, "name", "notes", "money", "kind");
	}
}

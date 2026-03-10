
package acme.entities.sponsorships;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface SponsorshipRepository extends AbstractRepository {

	@Query("SELECT SUM(d.money.amount) FROM Donation d WHERE d.sponsorship.id = :id AND d.money.currency = 'EUR'")
	Double calculateTotalMoney(int id);

	@Query("SELECT COUNT(d) FROM Donation d WHERE d.sponsorship.id = :id")
	int countDonationsBySponsorshipId(int id);
}

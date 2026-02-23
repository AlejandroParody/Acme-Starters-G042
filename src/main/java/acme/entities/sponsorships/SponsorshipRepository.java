
package acme.entities.sponsorships;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface SponsorshipRepository extends AbstractRepository {

	@Query("SELECT SUM(d.money.amount) FROM Donation d WHERE d.sponsorship.id = :id")
	Double calculateTotalMoney(@Param("id") int sponsorshipId);
}


package acme.features.any.sponsors;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.realms.Sponsor;

@Repository
public interface AnySponsorRepository extends AbstractRepository {

	@Query("select s from Sponsor s where s.id = :id")
	Sponsor findSponsorById(int id);
}

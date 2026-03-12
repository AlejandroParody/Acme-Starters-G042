
package acme.features.any.sponsorships;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.sponsorships.Sponsorship;

@Repository
public interface AnySponsorshipRepository extends AbstractRepository {

	@Query("select s from Sponsorship s where s.draftMode = false")
	public Collection<Sponsorship> findAllPublishedSponsorships();

	@Query("select s from Sponsorship s where s.id = :id")
	public Sponsorship findSponsorshipById(int id);

}

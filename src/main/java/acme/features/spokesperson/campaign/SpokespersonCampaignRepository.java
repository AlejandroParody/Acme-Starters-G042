
package acme.features.spokesperson.campaign;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;
import acme.entities.campaign.Milestone;

@Repository
public interface SpokespersonCampaignRepository extends AbstractRepository {

	@Query("select i from Campaign i where i.id = :id")
	Campaign findCampaignById(int id);

	@Query("select i from Campaign i where i.spokesperson.id = :spokespersonId")
	Collection<Campaign> findCampaignsBySpokespersonId(int spokespersonId);

	@Query("select p from Milestone p where p.campaign.id = :campaignId")
	Collection<Milestone> findMilestonesByCampaignId(int campaignId);
}

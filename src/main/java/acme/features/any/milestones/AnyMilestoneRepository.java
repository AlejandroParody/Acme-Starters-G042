
package acme.features.any.milestones;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;
import acme.entities.campaign.Milestone;

@Repository
public interface AnyMilestoneRepository extends AbstractRepository {

	@Query("select p from Milestone p where p.campaign.id = :campaignId")
	Collection<Milestone> findMilestonesByCampaignId(int campaignId);

	@Query("select p from Milestone p where p.id = :id")
	Milestone findMilestoneById(int id);

	@Query("select i from Campaign i where i.id = :id")
	Campaign findCampaignById(int id);
}


package acme.features.any.campaigns;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.campaign.Campaign;

@Repository
public interface AnyCampaignRepository extends AbstractRepository {

	@Query("select i from Campaign i where i.draftMode = false")
	public Collection<Campaign> findAllPublishedCampaigns();

	@Query("select i from Campaign i where i.id = :id")
	public Campaign findCampaignById(int id);

}

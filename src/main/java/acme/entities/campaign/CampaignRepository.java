
package acme.entities.campaign;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import acme.client.repositories.AbstractRepository;

public interface CampaignRepository extends AbstractRepository {

	@Query("select avg(m.effort) from Milestone m where m.campaign.id = :campaignId")
	Double computeAverageEffort(@Param("campaignId") int campaignId);
}

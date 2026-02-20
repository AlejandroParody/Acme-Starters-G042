
package acme.entities.strategies;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface StrategyRepository extends AbstractRepository {

	@Query("SELECT SUM(t.expectedPercentage) FROM Tactic t WHERE t.strategy.id = :id")
	Double calculateExpectedPercentage(@Param("id") int strategyId);
}

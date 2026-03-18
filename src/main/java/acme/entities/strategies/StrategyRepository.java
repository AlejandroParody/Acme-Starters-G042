
package acme.entities.strategies;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface StrategyRepository extends AbstractRepository {

	@Query("SELECT SUM(t.expectedPercentage) FROM Tactic t WHERE t.strategy.id = :id")
	Double calculateExpectedPercentage(int id);

	@Query("select s from Strategy s where s.ticker = :ticker")
	Strategy findStrategyByTicker(String ticker);

	@Query("select count(t) from Tactic t where t.strategy.id = :strategyId")
	long countTacticsByStrategy(int strategyId);
}

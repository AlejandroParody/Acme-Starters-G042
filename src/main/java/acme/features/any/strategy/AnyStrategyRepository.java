
package acme.features.any.strategy;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.strategies.Strategy;

@Repository
public interface AnyStrategyRepository extends AbstractRepository {

	@Query("select s from Strategy s where s.id = :id")
	Strategy findStrategyById(int id);

	@Query("select s from Strategy s where s.fundraiser.id = :id")
	Collection<Strategy> findStrategyByFundraiser(int id);

	@Query("select s from Strategy s where s.draftMode = false")
	Collection<Strategy> findAllPublishedStrategies();
}

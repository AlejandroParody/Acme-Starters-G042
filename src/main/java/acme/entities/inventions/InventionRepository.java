
package acme.entities.inventions;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface InventionRepository extends AbstractRepository {

<<<<<<< HEAD
	@Query("select sum(p.cost.amount) from Part p where p.invention.id = :inventionId")
	Double computeTotalCost(@Param("inventionId") int inventionId);
=======
	@Query("select sum(p.cost.amount) from Part p where p.invention.id = :id")
	Double computeTotalCost(int id);
>>>>>>> refs/remotes/origin/main

}


package acme.features.inventor.invention;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.inventions.Invention;

@Repository
public interface InventorInventionRepository extends AbstractRepository {

	@Query("select i from Invention i where i.inventor.id = :id ")
	Collection<Invention> findInventionsByInventorId(int id);

	@Query("select i from Invention i where i.id = :id")
	Invention findInventionById(int id);

}

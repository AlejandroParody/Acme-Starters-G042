
package acme.features.any.inventions;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.inventions.Invention;

@Repository
public interface AnyInventionRepository extends AbstractRepository {

	@Query("select i from Invention i where i.draftMode = false")
	public Collection<Invention> findAllPublishedInventions();

	@Query("select i from Invention i where i.id = :id")
	public Invention findInventionById(int id);

}


package acme.features.inventor.part;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.entities.inventions.Part;
import acme.realms.Inventor;

@Service
public class InventorPartListService extends AbstractService<Inventor, Part> {

	@Autowired
	private InventorPartRepository	repository;

	private Collection<Part>		parts;


	@Override
	public void authorise() {
		int inventionId;
		Invention invention;

		inventionId = super.getRequest().getData("inventionId", int.class);
		invention = this.repository.findInventionById(inventionId);

		super.setAuthorised(invention != null && invention.getInventor().isPrincipal());
	}

	@Override
	public void load() {
		int inventionId;
		Invention invention;

		inventionId = super.getRequest().getData("inventionId", int.class);
		invention = this.repository.findInventionById(inventionId);
		this.parts = this.repository.findPartsByInventionId(inventionId);

		super.getResponse().addGlobal("inventionId", inventionId);
		super.getResponse().addGlobal("published", !invention.getDraftMode());
	}

	@Override
	public void unbind() {
		super.unbindObjects(this.parts, "name", "kind", "cost");
	}
}

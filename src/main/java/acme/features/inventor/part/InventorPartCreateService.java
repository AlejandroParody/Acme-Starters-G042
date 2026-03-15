
package acme.features.inventor.part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractService;
import acme.datatypes.PartKind;
import acme.entities.inventions.Invention;
import acme.entities.inventions.Part;
import acme.realms.Inventor;

@Service
public class InventorPartCreateService extends AbstractService<Inventor, Part> {

	@Autowired
	private InventorPartRepository	repository;

	private Part					part;


	@Override
	public void load() {
		int inventionId;
		Invention invention;

		inventionId = super.getRequest().getData("inventionId", int.class);
		invention = this.repository.findInventionById(inventionId);

		this.part = super.newObject(Part.class);
		this.part.setInvention(invention);
	}

	@Override
	public void authorise() {
		boolean status;
		int inventionId;
		Invention invention;

		inventionId = super.getRequest().getData("inventionId", int.class);
		invention = this.repository.findInventionById(inventionId);

		status = invention != null && invention.getDraftMode() && invention.getInventor().isPrincipal();

		super.setAuthorised(status);
	}

	@Override
	public void bind() {
		super.bindObject(this.part, "name", "description", "cost", "kind");
	}

	@Override
	public void validate() {
		super.validateObject(this.part);
	}

	@Override
	public void execute() {
		this.repository.save(this.part);
	}

	@Override
	public void unbind() {
		Tuple tuple;
		SelectChoices choices;

		choices = SelectChoices.from(PartKind.class, this.part.getKind());

		tuple = super.unbindObject(this.part, "name", "description", "cost", "kind");

		tuple.put("kinds", choices);
		tuple.put("inventionId", this.part.getInvention().getId());
		tuple.put("published", !this.part.getInvention().getDraftMode());

		super.getResponse().addData(tuple);
	}
}

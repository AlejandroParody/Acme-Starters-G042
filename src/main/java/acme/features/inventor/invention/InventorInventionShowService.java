
package acme.features.inventor.invention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.components.models.Tuple;
import acme.client.services.AbstractService;
import acme.entities.inventions.Invention;
import acme.realms.Inventor;

@Service
public class InventorInventionShowService extends AbstractService<Inventor, Invention> {

	@Autowired
	private InventorInventionRepository	repository;

	private Invention					invention;


	@Override
	public void load() {
		int id;

		id = super.getRequest().getData("id", int.class);
		this.invention = this.repository.findInventionById(id);
	}

	@Override
	public void authorise() {
		boolean status;

		status = this.invention != null && this.invention.getInventor().isPrincipal();

		super.setAuthorised(status);
	}

	@Override
	public void unbind() {
		Tuple tuple;
		String published;

		tuple = super.unbindObject(this.invention, "ticker", "name", "description", "startMoment", "endMoment", "moreInfo");

		if (super.getRequest().getLocale().getLanguage().equals("es"))
			published = Boolean.TRUE.equals(this.invention.getDraftMode()) ? "No" : "Sí";
		else
			published = Boolean.TRUE.equals(this.invention.getDraftMode()) ? "No" : "Yes";

		tuple.put("published", published);
		tuple.put("inventorId", this.invention.getInventor().getId());
		tuple.put("monthsActive", this.invention.monthsActive());
		tuple.put("cost", this.invention.cost());
		tuple.put("draftMode", this.invention.getDraftMode());

		super.getResponse().addData(tuple);
	}
}

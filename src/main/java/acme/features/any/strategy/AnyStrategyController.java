
package acme.features.any.strategy;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.strategies.Strategy;
import acme.realms.Fundraiser;

@Controller
public class AnyStrategyController extends AbstractController<Fundraiser, Strategy> {

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", AnyStrategyListService.class);
		super.addBasicCommand("show", AnyStrategyShowService.class);
	}

}

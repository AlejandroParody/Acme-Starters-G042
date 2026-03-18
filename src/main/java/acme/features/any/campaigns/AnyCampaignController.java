
package acme.features.any.campaigns;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.components.principals.Any;
import acme.client.controllers.AbstractController;
import acme.entities.campaign.Campaign;

@Controller
public class AnyCampaignController extends AbstractController<Any, Campaign> {

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", AnyCampaignListService.class);
		super.addBasicCommand("show", AnyCampaignShowService.class);
	}
}

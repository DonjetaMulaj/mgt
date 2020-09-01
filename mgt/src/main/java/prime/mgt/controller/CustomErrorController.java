package prime.mgt.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Donjeta Mulaj <donjeta.mulaj@asseco-see.com>
 */
@Controller
public class CustomErrorController implements ErrorController {
	private static final String PATH = "/error";

	@Override
	public String getErrorPath() {
		return PATH;
	}

	@RequestMapping(value = PATH)
	public String error(HttpServletResponse response) {
		return getResponse(response.getStatus());
	}

	private String getResponse(int status) {
		return "<!DOCTYPE html><html><head><title>HTTP "
				+ status
				+ "</title><link href=\"https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/cerulean/bootstrap.min.css\" rel=\"stylesheet\" /></head>"
				+ "<body><br/><div class=\"container\"><div class=\"row\"><div class=\"panel panel-warning\"><div class=\"panel-heading\"><h3 class=\"panel-title\">HTTP "
				+ status
				+ "</h3></div>"
				+ "<div class=\"panel-body\">The requested page could not be found or there is another problem with your request. Please check and try again.</div>"
				+ "</div></div></div></body></html>";
	}
}

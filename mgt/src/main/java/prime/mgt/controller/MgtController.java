package prime.mgt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Controller("/mgt")
public class MgtController {
	@CrossOrigin
	@GetMapping(value = "/mgt/testform")
	public String getOpenPaymentPage(HttpServletRequest request) {
		return "";
	}
}

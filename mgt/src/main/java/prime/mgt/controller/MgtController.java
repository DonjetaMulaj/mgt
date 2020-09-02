package prime.mgt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class MgtController {
	@CrossOrigin
	@GetMapping(value = "/testform")
	public String getOpenPaymentPage(HttpServletRequest request) {
		return "";
	}
}

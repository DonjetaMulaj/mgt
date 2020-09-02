package prime.mgt.service.impl;

import org.springframework.stereotype.Component;

import prime.mgt.domain.User;

@Component
public interface UserService {
	
	User getUser(String userName, String userEmail, String userPassword);
}

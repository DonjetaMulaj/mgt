package prime.mgt.service.spec;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prime.mgt.domain.User;
import prime.mgt.domain.spec.UserDao;
import prime.mgt.service.impl.UserService;

@Component
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	@Override
	public User getUser(String userName, String userEmail, String userPassword) {
		User user = userDao.getUser();
		boolean isUserNameSet = StringUtils.isNoneBlank(userName);
		boolean isUserEmailSet = StringUtils.isNotBlank(userEmail);
		if (user != null && isUserNameSet && userName.equals(user.getUserName()) && userPassword.equals(user.getUserPassword())) {
			return user;
		}
		if (user != null && isUserEmailSet && userEmail.equals(user.getUserEmail()) && userPassword.equals(user.getUserPassword())) {
			return user;
		}
		return null;
	}
}

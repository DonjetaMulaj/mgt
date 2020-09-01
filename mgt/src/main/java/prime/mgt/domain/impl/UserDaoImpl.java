package prime.mgt.domain.impl;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import prime.mgt.api.util.DateUtils;
import prime.mgt.domain.User;
import prime.mgt.domain.enums.UserRole;
import prime.mgt.domain.spec.UserDao;

@Component
public class UserDaoImpl implements UserDao{
	@Autowired
	private DateUtils dateUtils;
	
	@Override
	public User getUser() {
		return new User.Builder().userCreatedTs(dateUtils.getNow()).userName("primeAuthUser").userPassword("Test1234").userRoles(new HashSet<UserRole>(Arrays.asList(UserRole.BASIC))).build();
	}}

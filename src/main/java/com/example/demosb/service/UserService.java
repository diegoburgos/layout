package com.example.demosb.service;

import com.example.demosb.dto.UserDTO;
import com.example.demosb.entity.User;
import com.example.demosb.exception.ErrorNewUser;
import com.example.demosb.repository.UserRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
public class UserService {

	private static final Pattern MAIL_PATTERN = Pattern.compile("^[\\w\\.]+@([\\w-]+)+\\.[\\w-]{2,4}$");
	private static final Set<String> excludedMailServices = new HashSet<>() {{
		add("yopmail");
	}};

	@Autowired
	@Getter
	UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User add(UserDTO userdto) throws ErrorNewUser {
		isValidEmail(userdto.getEmail());
		User prev = getUserRepository().findByEmail(userdto.getEmail());
		if (prev != null) {
			throw new ErrorNewUser("User with email " + userdto.getEmail() + " already registered");
		}
		User user = new User(userdto.getFirstname(), userdto.getLastname(), userdto.getPassword(), userdto.getEmail());
		getUserRepository().save(user);
		return user;
	}

	public boolean isValidEmail(String mail) throws ErrorNewUser {
		log.debug("isValidEmail(): mail:{}", mail);
		Matcher matcher = MAIL_PATTERN.matcher(mail);
		if (!matcher.matches()) {
			throw new ErrorNewUser("No valid mail:" + mail);
		}
		String mailSrv = matcher.group(1);
		log.debug("isValidEmail(): mail server:{}", mailSrv);
		if (excludedMailServices.contains(mailSrv)) {
			throw new ErrorNewUser("No valid mail of server " + mailSrv);
		}
		return true;
	}
}

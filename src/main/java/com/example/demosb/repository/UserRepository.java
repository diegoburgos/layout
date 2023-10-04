package com.example.demosb.repository;

import com.example.demosb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	@Override
	void delete(User user);

	User findByEmail(String email);
}

package com.example.demosb.repository;

import com.example.demosb.entity.Layout;
import com.example.demosb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LayoutRepository extends JpaRepository<Layout, Integer> {

	@Query(value = "UPDATE Layout SET rColor=0 gColor=0 bColor=0", nativeQuery = true)
	void reset();
}

package com.example.demosb.api;

import com.example.demosb.dto.LayoutDTO;
import com.example.demosb.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class TestLayoutAPI {

	@Value("${layout.size}")
	private int layoutSize;
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void getClustersShouldReturnEmptyList() {
		ResponseEntity<String> response = testRestTemplate.getForEntity("/api/v1/layout", String.class);
		log.warn("response: {}", response);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		LayoutDTO change = new LayoutDTO(0, 0, 1, 1, 1);
		log.warn("change:{}", change);
		response = testRestTemplate.postForEntity("/api/v1/layout/set", change, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		log.warn("change resp:{}", response.getBody());
		response = testRestTemplate.getForEntity("/api/v1/layout", String.class);
		log.warn("response: {}", response);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		testRestTemplate.put("/api/v1/reset", null);
	}

}

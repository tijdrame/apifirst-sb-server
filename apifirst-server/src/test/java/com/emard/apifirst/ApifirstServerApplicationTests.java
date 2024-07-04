package com.emard.apifirst;

import com.emard.apifirst.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApifirstServerApplicationTests {

	@Autowired
	CustomerRepository repository;
	@Test
	void contextLoads() {
		assertThat(repository.count()).isGreaterThan(0L);
	}

}

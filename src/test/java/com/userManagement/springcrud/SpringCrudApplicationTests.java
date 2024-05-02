package com.userManagement.springcrud;

import com.userManagement.springcrud.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringCrudApplicationTests {

	@Autowired
	UserRepository userRepository;


	@Test
	void contextLoads() {
	}

	@Test
	void getById(){
		userRepository.findById(19);
	}

}

package com.wallet.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wallet.entity.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {
	
	public static final String EMAIL = "tatiane@email.com";
	
	@Autowired
	UserRepository repository;
	
	
	// @Before realiza isntruções antes de passar pelos testes
	// persiste na base de dados um User
	@Before
	public void setUp() {
		User user = new User();
		user.setName("Tatiane Muniz");
		user.setEmail(EMAIL);
		user.setPassword("020416");
		
		repository.save(user);
	}
	
	
	// @After executa isntruções depois dos testes
	//deleta o User salvo na base de dados
	@After
	public void tearDown() {
		repository.deleteAll();
	}
	
	@Test
	public void testSave() {
		//mock
		User user = new User();
		user.setName("Tatiane Muniz");
		user.setEmail(EMAIL);
		user.setPassword("020416");
		
		//ação
		User response = repository.save(user);
		
		//validação
		assertNotNull(response);
	}
	
	@Test
	public void testFindByEmail() {
		// cenário - ação
		Optional<User> user = repository.findByEmailEquals(EMAIL);
		
		// validação
		assertTrue(user.isPresent());
		assertEquals(user.get().getEmail(), EMAIL);
	}

}

package com.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.dao.UserRepository;
import com.entity.Role;
import com.entity.User;
import static org.mockito.ArgumentMatchers.any;




public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository; // @Mock and @InjectMocks: These are Mockito annotations. @Mock creates mock objects for testing without actually hitting the database or other services. 
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks   //@InjectMocks injects the mocked objects into the class we're testing (UserService).
	private UserService userService;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this); // Initializes the @Mock and @InjectMocks annotations before each test, setting up the necessary mocks.
	}
	
	@Test
	void registeredUser_ShouldSaveUserWithEncodedPassword() {
		
		User user = new User();
		user.setEmail("userDemo1@example.com");
		user.setPassword("plainpassword1");
		
		when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword1");
		when(userRepository.save(any(User.class))).thenReturn(user);
		
		//Act
		User savedUser = userService.registerUser(user);  //Action: We call userService.registerUser(user) to register the user. The registerUser method should encode the password, set the role to USER, and save the user using userRepository.
		
		//Assert
		assertNotNull(savedUser, "Saved User should not be null");
		assertEquals( savedUser.getPassword(),"encodedPassword1", "Password should be encoded");
		assertEquals(savedUser.getRole(), Role.USER, "Role should be set to USER" );
		verify(userRepository, times(1)).save(user);		
	}
	
	@Test
	void findByEmail_ShouldReturnUser_WhenUserExists() {
		
		User user = new User();
		user.setEmail("userDemo1@example.com");
		
		when(userRepository.findByEmail("userDemo1@example.com")).thenReturn(user);
		
		//Act
		User foundUser = userService.findByEmail("userDemo1@example.com");
		
		assertNotNull(foundUser);
		assertEquals(foundUser.getEmail(),"userDemo1@example.com");
		verify(userRepository, times(1)).findByEmail("userDemo1@example.com");
		
	}
	
	
	

}

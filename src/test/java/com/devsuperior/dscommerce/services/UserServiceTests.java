package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.projections.UserDetailsProjection;
import com.devsuperior.dscommerce.repositories.UserRepository;
import com.devsuperior.dscommerce.tests.UserDetailsFactory;
import com.devsuperior.dscommerce.tests.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@Tag("Unit")
@ExtendWith(SpringExtension.class)
public class UserServiceTests {

    @InjectMocks
    private UserServices userServices;

    @Mock
    private UserRepository userRepository;

    private String existingUserName, nonExistingUserName;
    private User user;
    private List<UserDetailsProjection> userDetails;

    @BeforeEach
    void setUp() {
        existingUserName = "existingUserName@gmail.com";
        nonExistingUserName = "nonExistingUserName@gmail.com";

        user = UserFactory.createCustomAdminUser(1L, existingUserName);
        userDetails = UserDetailsFactory.createCustomClientUser(existingUserName);

        Mockito.when(userRepository.searchUserAndRolesByEmail(existingUserName)).thenReturn(userDetails);
        Mockito.when(userRepository.searchUserAndRolesByEmail(nonExistingUserName)).thenReturn(new ArrayList<>());
    }

    @Test
    public void loadUserByUsernameShouldReturnUserDetailsWhenUserExists() {
        UserDetails result = userServices.loadUserByUsername(existingUserName);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getUsername(), existingUserName);

        Mockito.verify(userRepository, Mockito.times(1)).searchUserAndRolesByEmail(existingUserName);
    }

    @Test
    public void loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExist() {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userServices.loadUserByUsername(nonExistingUserName);
        });

        Mockito.verify(userRepository, Mockito.times(1)).searchUserAndRolesByEmail(nonExistingUserName);
    }
}

package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.UserDTO;
import com.devsuperior.dscommerce.entities.Role;
import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.projections.UserDetailsProjection;
import com.devsuperior.dscommerce.repositories.UserRepository;
import com.devsuperior.dscommerce.services.exceptions.UserNotLoggedException;
import com.devsuperior.dscommerce.utils.CustomUserUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServices implements UserDetailsService {

    private final UserRepository userRepository;
    private final CustomUserUtil customUserUtil;

    public UserServices(UserRepository userRepository, CustomUserUtil customUserUtil) {
        this.userRepository = userRepository;
        this.customUserUtil = customUserUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = userRepository.searchUserAndRolesByEmail(username);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = new User();
        user.setEmail(username);
        user.setPassword(result.getFirst().getPassword());
        for (UserDetailsProjection userDetails : result) {
            user.addRole(new Role(
                    userDetails.getRoleId(),
                    userDetails.getAuthority()
            ));
        }

        return user;
    }

    @Transactional(readOnly = true)
    public UserDTO getCurrentUser() {
        User user = this.authenticated();
        return new UserDTO(user);
    }

    protected User authenticated() {
        try {
            String username = customUserUtil.getLoggedUserName();

            return userRepository.findByEmail(username).orElseThrow(
                    UserNotLoggedException::new
            );
        } catch (ClassCastException e) {
            throw new UsernameNotFoundException("User not found");
        }
    }
}

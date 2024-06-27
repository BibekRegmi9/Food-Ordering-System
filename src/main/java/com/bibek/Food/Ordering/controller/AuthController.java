package com.bibek.Food.Ordering.controller;

import com.bibek.Food.Ordering.config.JwtProvider;
import com.bibek.Food.Ordering.entity.Cart;
import com.bibek.Food.Ordering.entity.User;
import com.bibek.Food.Ordering.enums.USER_ROLE;
import com.bibek.Food.Ordering.repository.CartRepo;
import com.bibek.Food.Ordering.repository.UserRepo;
import com.bibek.Food.Ordering.request.LoginRequest;
import com.bibek.Food.Ordering.service.CustomerUserDetailsService;
import com.bibek.Food.Ordering.utils.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private CartRepo cartRepo;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        User isEmailExist = userRepo.findByEmail(user.getEmail());

        if(isEmailExist != null){
            throw new Exception("Email already in use");
        }

        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        createdUser.setRole(user.getRole());
        createdUser.setPassword(user.getPassword());

        User savedUser = userRepo.save(createdUser);

        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepo.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registration success");
        authResponse.setRole(savedUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

@PostMapping("/signin")
    public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest request) throws Exception {
        Authentication authentication = authenticate(request.getEmail(), request.getPassword());

        Collection<? extends GrantedAuthority> authority = authentication.getAuthorities();
        String role = authority.isEmpty() ? null : authority.iterator().next().getAuthority();
        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Success");
        authResponse.setRole(USER_ROLE.valueOf(role));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String email, String password) throws Exception {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);

        if(userDetails == null){
            throw new Exception("Enter email and password.");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Username or Password dont match");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}

package az.springlesson.travel.travel.controller;

import az.springlesson.travel.travel.dao.AuthRequest;
import az.springlesson.travel.travel.dao.JwtResponse;
import az.springlesson.travel.travel.dao.UserRequest;
import az.springlesson.travel.travel.entity.UserEntity;
import az.springlesson.travel.travel.security.JwtUtil;
import az.springlesson.travel.travel.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ValidationAutoConfiguration validationAutoConfiguration;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, ValidationAutoConfiguration validationAutoConfiguration, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.validationAutoConfiguration = validationAutoConfiguration;
        this.userService = userService;
    }

    @PostMapping("/login")
    public JwtResponse AuthenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return JwtResponse.builder()
                    .accessToken(jwtUtil.generateToken(authRequest.getUsername())).build();
        }
        else{
            throw new UsernameNotFoundException("Invalid user request !!! ");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registration(@RequestBody @Valid UserRequest userRequest){
        UserEntity existingUser =userService.findUserByEmail(userRequest.getEmail());
        if(existingUser != null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()){
            return ResponseEntity.ok("User already exists !!! ");
        }
        else {
            userService.saveUser(userRequest);
            return ResponseEntity.ok("Successfully registered  !!! ");
        }
    }




}

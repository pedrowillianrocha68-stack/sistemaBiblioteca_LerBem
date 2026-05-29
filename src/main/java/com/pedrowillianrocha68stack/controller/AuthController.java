package com.pedrowillianrocha68stack.controller;

import com.pedrowillianrocha68stack.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
   
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    public record LoginRequest(String username, String password){}

    public record LoginResponse(String token){}

    @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginRequest request){
           try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos");
        }
    UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
    String token = jwtUtil.generateToken(userDetails);

    return ResponseEntity.ok(new LoginResponse(token));
    
   } 
}

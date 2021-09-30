package com.efa.windoor.admin.controllers;


import com.efa.windoor.admin.filters.JwtUtils;
import com.efa.windoor.admin.model.AdminResponse;
import com.efa.windoor.admin.serivces.UserService;
import com.efa.windoor.admin.config.MyUserDetailsService;
import com.efa.windoor.admin.utils.Utils;
import com.efa.windoor.core.dtos.AuthenticationRequest;
import com.efa.windoor.core.dtos.AuthenticationResponse;
import com.efa.windoor.core.dtos.UserDto;
import com.efa.windoor.core.dtos.user.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/api/login/account")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest auth) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(auth.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);


        return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(jwt, "ok", userDetails.getUsername(), "account"));
    }

    @GetMapping("/login{logout}")
    public void logoutAndDeleteUserToken(@PathVariable("logout") String logout) {
        //@Todo: service need to be written
        System.out.println(Utils.getUsername());
    }

    @GetMapping(path = "/user")
    public ResponseEntity<UserDto> getUserDetails() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getCurrentUserDetails());
    }

    @GetMapping(path = "/api/currentUser")
    public ResponseEntity<AdminResponse> getCurrentUser() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(AdminResponse.builder().success(true).data(userService.getCurrentUser()).build());
    }


}

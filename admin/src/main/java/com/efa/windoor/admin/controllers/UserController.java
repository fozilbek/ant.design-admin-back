package com.efa.windoor.admin.controllers;

import com.efa.windoor.admin.serivces.UserService;
import com.efa.windoor.core.dtos.ModifiedResponse;
import com.efa.windoor.core.dtos.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;


    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getUsersList();
    }

    @PostMapping("/create")
    public ResponseEntity<ModifiedResponse> createUser(@RequestBody UserDto dto) {

        ModifiedResponse response = userService.saveBackendUser(dto);

        if (response.getError()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable(name = "id") Long id) {
        userService.deleteUserById(id);
    }

}

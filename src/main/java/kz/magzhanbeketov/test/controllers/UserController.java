package kz.magzhanbeketov.test.controllers;

import kz.magzhanbeketov.test.dto.UserDTO;
import kz.magzhanbeketov.test.exception.BadRequestException;
import kz.magzhanbeketov.test.exception.NotFoundException;
import kz.magzhanbeketov.test.models.User;
import kz.magzhanbeketov.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username){
        try {
            User user = userService.getUser(username);
            return ResponseEntity.ok(user);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO user){
        try {
            User newUser = userService.addUser(user);
            return ResponseEntity.ok(newUser);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        try {
            User updateUser = userService.updateUser(user);
            return ResponseEntity.ok(updateUser);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteByUsername/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){
        try {
            User deleteUser = userService.deleteUser(username);
            return ResponseEntity.ok(deleteUser);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

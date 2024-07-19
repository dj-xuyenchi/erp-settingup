package app.au.apigateway.controller;

import app.au.apigateway.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class CustomerController {
  //  private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<Customer> createUser(@RequestBody Customer user) {
        Customer createdUser = new Customer();
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getUsers() {
        List<Customer> users = new ArrayList<>();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<String> getUser(@PathVariable Long userId) {
     //   Customer user = userService.getUserById(userId);
        return ResponseEntity.ok("123");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody Customer user) {
   //     Customer updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.ok("updatedUser");
    }
}
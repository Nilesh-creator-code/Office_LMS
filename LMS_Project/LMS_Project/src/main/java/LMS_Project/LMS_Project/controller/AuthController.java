package LMS_Project.LMS_Project.controller;

import LMS_Project.LMS_Project.dto.LoginDto;
import LMS_Project.LMS_Project.dto.UserDto;
import LMS_Project.LMS_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from AuthController";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        String response = userService.addNewUser(userDto);
        return ResponseEntity.ok(response);
    }


    // For login
    @PostMapping("/login")    
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto) {
        String loginResponse = userService.LoginUser(loginDto);
        System.out.println(loginDto.getUserName());
        return ResponseEntity.ok(loginResponse);
    }

}
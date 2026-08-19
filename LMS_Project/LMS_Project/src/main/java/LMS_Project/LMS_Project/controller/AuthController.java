package LMS_Project.LMS_Project.controller;

import LMS_Project.LMS_Project.dto.UserDto;
import LMS_Project.LMS_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    private UserService userService;


    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from AuthControler";
    }


    @PostMapping("/register")
    public String registerUser(UserDto userDto) {
        return userService.addNewUser(userDto);
    }




}

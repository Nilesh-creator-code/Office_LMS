package LMS_Project.LMS_Project.service;

import LMS_Project.LMS_Project.dto.UserDto;
import LMS_Project.LMS_Project.entity.User;
import LMS_Project.LMS_Project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String addNewUser(UserDto userDto) {

        if (userRepository.existsByUsername(userDto.getUsername())) {
            return "Username already exists";
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            return "Email already exists";
        }

        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());

        userRepository.save(user);

        return "User added successfully";
    }


}
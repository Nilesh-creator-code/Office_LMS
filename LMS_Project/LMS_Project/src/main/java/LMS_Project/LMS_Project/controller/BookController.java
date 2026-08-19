package LMS_Project.LMS_Project.controller;


import LMS_Project.LMS_Project.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import LMS_Project.LMS_Project.dto.BookDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Book Controller";
    }


    // Add new book By user
    @PostMapping("/add")
    public ResponseEntity<String> addNewBook(@Valid @RequestBody BookDto bookDto) {

        String response = bookService.addNewBook(bookDto);

        return ResponseEntity.ok(response);
        
        
    };

}

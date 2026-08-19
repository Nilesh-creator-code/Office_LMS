package LMS_Project.LMS_Project.controller;


import LMS_Project.LMS_Project.service.BookService;

import java.util.List;

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

    // Update book by user
    @PutMapping("/update/{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable Long bookId, @Valid @RequestBody BookDto bookDto) {
        String response = bookService.updateBook(bookId, bookDto);
        return ResponseEntity.ok(response);
    }



    // Get All book by user
    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
    

}

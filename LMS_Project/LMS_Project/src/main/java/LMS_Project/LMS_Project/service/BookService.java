package LMS_Project.LMS_Project.service;

import LMS_Project.LMS_Project.dto.BookDto;
import LMS_Project.LMS_Project.entity.Book;
import LMS_Project.LMS_Project.entity.User;
import LMS_Project.LMS_Project.repository.BookRepository;
import LMS_Project.LMS_Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    // Add new book by logged-in user
    public String addNewBook(BookDto bookDto) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        System.out.println("Authenticated username: " + username);

        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with username: " + username
                        )
                );

        Book book = Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .description(bookDto.getDescription())
                .user(currentUser)
                .build();

        bookRepository.save(book);

        return "Book added successfully";
    }

    public BookDto getBookDetail(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Book not found with id: " + bookId
                        )
                );

        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription()
        );
    }


    public String updateBook(Long bookId, BookDto bookDto) {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String username = authentication.getName();

    User currentUser = userRepository.findByUsername(username)
            .orElseThrow(() ->
                    new RuntimeException(
                            "User not found with username: " + username
                    )
            );

    Book book = bookRepository.findById(bookId)
            .orElseThrow(() ->
                    new RuntimeException(
                            "Book not found with id: " + bookId
                    )
            );

    // Make sure the logged-in user owns this book
    if (!book.getUser().getId().equals(currentUser.getId())) {
        throw new RuntimeException(
                "You are not authorized to update this book"
        );
    }

    // Update book fields
    book.setTitle(bookDto.getTitle());
    book.setAuthor(bookDto.getAuthor());
    book.setDescription(bookDto.getDescription());

    bookRepository.save(book);

    return "Book updated successfully";
}

public List<BookDto> getAllBooksByUser() {
    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    String username = authentication.getName();

    User currentUser = userRepository.findByUsername(username)
            .orElseThrow(() ->
                    new RuntimeException(
                            "User not found with username: " + username
                    )
            );

    List<Book> books = bookRepository.findByUser(currentUser);

    return books.stream()
            .map(book -> new BookDto(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getDescription()
            ))
            .collect(Collectors.toList());
        }


}
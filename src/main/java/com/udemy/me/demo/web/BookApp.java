package com.udemy.me.demo.web;

import com.udemy.me.demo.domain.Book;
import com.udemy.me.demo.sevice.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BookApp {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public Page<Book> getAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5")int size){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return bookService.findAllByPage(PageRequest.of(page,size,sort));
        //return bookService.findAll();
    }

    @PostMapping("/books")
    public Book post(Book book
                    /*@RequestParam String name,
                     @RequestParam String author,
                     @RequestParam String description,
                     @RequestParam int status*/){
    /*    Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);
*/        return bookService.save(book);
    }

    @GetMapping("/books/{id}")
    public Book getOne(@PathVariable long id){
        return bookService.findOne(id);
    }

    @PutMapping("/books")
    public Book update(@RequestParam long id,
                       @RequestParam String name,
                       @RequestParam String author,
                       @RequestParam String description,
                       @RequestParam int status){
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);
        return bookService.save(book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteOne(@PathVariable long id){
        bookService.deleteOne(id);
    }
    @PostMapping("/books/by")
    public List<Book> findBy(@RequestParam String author){
        return bookService.findByAuthor(author);
    }
    @PostMapping("/books/byand")
    public List<Book> findByAuthorAndStatus(@RequestParam String author,@RequestParam int status){
        return bookService.findByAuthorAndStatus(author,status);
    }
    @PostMapping("/books/byandwith")
    public List<Book> findByDescriptionEndsWith(@RequestParam String description){
        return bookService.findByDescriptionEndsWith(description);
    }
    @PostMapping("/books/bycon")
    public List<Book> findByDescriptionContains(@RequestParam String description){
        return bookService.findByDescriptionContains(description);
    }
    @PostMapping("/books/fJPQL")
    public List<Book> findByJPQL(@RequestParam int len){
        return bookService.findByJPQL(len);
    }
    @PostMapping("/books/uJPQL")
    public int updateByJPQL(@RequestParam int status,@RequestParam long id){
        return bookService.updateByJPQL(status,id);
    }
    @PostMapping("/books/dJPQL")
    public int deleteByJPQL(@RequestParam long id){
        return bookService.deleteByJPQL(id);
    }
}

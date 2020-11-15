package com.udemy.me.demo.web;

import com.udemy.me.demo.domain.Book;
import com.udemy.me.demo.sevice.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller/*外部控制器*/
public class BookController {
    @Autowired
    private BookService bookService;
    /**/
    @GetMapping("/books")
    public String list(Model model) {
        List<Book> book =bookService.findAll();
        model.addAttribute("books",book);
        return "books";
    }
    /*書單詳情*/
    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id, Model model){
        Book book =bookService.findOne(id);
        model.addAttribute("book",book);
        return "book";
    }
    /*新增請求*/
    @GetMapping("/books/input")
    public String inputPage(Model model){
        model.addAttribute("book",new Book());
        return "input";
    }
    /*新增儲存提交後返回列表利用redirect重新導向*/
    @PostMapping("/books")
    public String post(Book book) {
        bookService.save(book);
        return "redirect:/books";
    }
    /*列表中更新書單內容*/
    @GetMapping("/books/{id}/input")
    public String inputEditPage(@PathVariable long id,Model model){
        Book book = bookService.findOne(id);
        model.addAttribute("book",book);
        return "input";
    }
    /*列表中刪除書單內容*/
    @GetMapping("/books/{id}/delete")
    public String delete(@PathVariable long id){
        bookService.deleteOne(id);
        return "redirect:/books";
    }

}

package com.udemy.me.demo.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")

public class HelloController {
    @PostMapping("/says")
    public String hello(){
        return "hello";
    }
    //@GetMapping("/books")
    public Object getAll(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","hello");
        map.put("age",18);

        return map;
    }
    @GetMapping("/books/{id}")
    public Object getOne(@PathVariable long id){

        return null;
    }

    @PostMapping("/books")
    public Object post(@RequestParam String name,
                       @RequestParam String author,
                       @RequestParam String isbn){
        Map<String,Object> book = new HashMap<String,Object>();
        book.put("name",name);
        book.put("author",author);
        book.put("isbn",isbn);

        return book;
    }
    @GetMapping("/books")
    public Object getAll(@RequestParam int page,
                         @RequestParam(defaultValue = "10") int size){
        Map<String,Object> book = new HashMap<String,Object>();
        book.put("name","互聯網");
        book.put("isbn","33242");
        book.put("author","David Wang");
        Map<String,Object> book2 = new HashMap<String,Object>();
        book2.put("name","不聯網");
        book2.put("isbn","335411");
        book2.put("author","David Wang");

        List<Map> content = new ArrayList<>();
        content.add(book);
        content.add(book2);

        Map<String,Object> pagemap =new HashMap<>();
        pagemap.put("page",page);
        pagemap.put("size",size);
        pagemap.put("content",content);
        return pagemap;
    }

}

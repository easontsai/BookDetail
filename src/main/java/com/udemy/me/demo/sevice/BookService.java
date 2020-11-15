package com.udemy.me.demo.sevice;

import com.udemy.me.demo.domain.Book;
import com.udemy.me.demo.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service/*標記成Service層*/
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    /*引入接口*/

    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    public Page<Book> findAllByPage(Pageable pageable){
/*        Sort sort = Sort.by(Sort.Direction.DESC,"id,name");
        Pageable pageable = PageRequest.of(1,5);*/
        return bookRepository.findAll(pageable);
    }
    public Book save(Book book){
        return bookRepository.save(book);
    }

    public Book findOne(long id){
        return bookRepository.findById(id).get();
    }

    public void deleteOne(long id){
        bookRepository.deleteById(id);
    }

    /*查詢書單列表*/
    public List<Book> findByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findByAuthorAndStatus(String author,int status){
        return bookRepository.findByAuthorAndStatus(author,status);
    }
    public List<Book> findByDescriptionEndsWith(String des){
        return bookRepository.findByDescriptionEndsWith(des);
    }
    public List<Book> findByDescriptionContains(String des){
        return bookRepository.findByDescriptionContains(des);
    }

    public List<Book> findByJPQL(int len){
        return bookRepository.findByJPQL(len);
    }

    public int updateByJPQL(int status,long id ){
        return bookRepository.updateByJPQL(status,id);
    }
    public int deleteByJPQL(long id ){
        return bookRepository.deleteByJPQL(id);
    }


}

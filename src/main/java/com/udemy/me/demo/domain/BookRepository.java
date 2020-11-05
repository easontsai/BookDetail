package com.udemy.me.demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    /*分頁處理 查詢所有資訊列表 變量需對應方法*/
    Page<Book> findAll(Pageable pageable);

    /*Query規則*/
    List<Book> findByAuthor(String Author);

    List<Book> findByAuthorAndStatus(String author,int status);

    List<Book> findByDescriptionEndsWith(String des);

    List<Book> findByDescriptionContains(String des);


    /*自定義查詢語句*/
/*
    @Query("select b from Book b where length(b.name) > ?1")
*/
    @Query(value = "select * from book where length(name) > ?1",nativeQuery = true)
    List<Book> findByJPQL(int len);

    /*自訂義更新*/
    @Transactional
    @Modifying
    @Query("update Book b set b.status = ?1 where b.id = ?2 ")
    int updateByJPQL(int status,long id);
    @Transactional
    @Modifying
    @Query("delete from Book b  where b.id = ?1 ")
    int deleteByJPQL(long id);


}

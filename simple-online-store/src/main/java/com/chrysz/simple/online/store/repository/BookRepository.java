package com.chrysz.simple.online.store.repository;

import com.chrysz.simple.online.store.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}

package ru.nicolas.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.nicolas.models.Book;
import ru.nicolas.models.Person;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{id}, new BookMapper())
                .stream().findAny().orElse(null);

    }

    public Optional<Book> show(String title) {
        return jdbcTemplate.query("SELECT * FROM book WHERE title=?", new Object[]{title},
                        new BookMapper())
                .stream().findAny();

    }

    public void save(Book book) {

        jdbcTemplate.update("INSERT INTO Book(title , year, author) VALUES(?, ?, ?)",
                book.getTitle(), book.getYear(), book.getAuthor());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, year=? , author=? WHERE book_id=?",
                updatedBook.getTitle(), updatedBook.getYear(), updatedBook.getAuthor(), id);

    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

    public void makeOrder(int book_id, Person customer) {

        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?"
        ,                 customer.getId(), book_id);

    }
    public void closeOrder(int id) {


        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE book_id=?", id);
    }

    public List<Book>  showOrdered (int id) {

        return jdbcTemplate.query("SELECT * from book JOIN  person on person.person_id = book.person_id where person.person_id=?",new Object[]{id}, new BookMapper());

    }

}


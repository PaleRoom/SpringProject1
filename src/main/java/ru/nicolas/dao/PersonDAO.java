package ru.nicolas.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.nicolas.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
        //return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE name=?", new Object[]{name},
                        new PersonMapper())
                .stream().findAny();

    }

    public void save(Person person) {

        jdbcTemplate.update("INSERT INTO Person(name , born) VALUES(?, ?)",
                person.getName(), person.getBorn());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, born=? WHERE person_id=?",
                updatedPerson.getName(), updatedPerson.getBorn(),id);

    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
    }

    public List<Person>  showCustomer (int id) {
        return jdbcTemplate.query("SELECT * from person JOIN book on person.person_id = book.person_id where book_id=?",new Object[]{id}, new PersonMapper());
    }
}


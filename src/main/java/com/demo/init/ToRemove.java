package com.demo.init;

import com.demo.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ToRemove {

    private static final Logger log = LoggerFactory.getLogger(ToRemove.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void toRemove1() {
        this.jdbcTemplate.query("select * from person", (rs, i) -> toPerson(rs))
                .forEach(person -> log.info("Person: {}", person));
    }

    @PostConstruct
    public void toRemove2() {
        final List<Person> persons = this.jdbcTemplate.query("select * from person", new PersonRowMapper());
        persons.forEach(System.out::println);
    }

    private RowMapper<Person> personRowMapper = (rs, rowNum) -> toPerson(rs);

    private class PersonRowMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(final ResultSet rs, final int rowNum) throws SQLException {
            final Person person = new Person();

            person.setId(rs.getInt("id"));
            person.setAge(rs.getInt("age"));
            person.setName(rs.getString("name"));

            return person;
        }
    }

    private Person toPerson(final ResultSet resultSet) throws SQLException {
        final Person person = new Person();

        person.setId(resultSet.getInt("id"));
        person.setAge(resultSet.getInt("age"));
        person.setName(resultSet.getString("name"));

        return person;
    }

}

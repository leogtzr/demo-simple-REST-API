package com.demo.web;

import com.demo.domain.Person;
import com.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("person")
    public ResponseEntity<List<Person>> getPerson() {
        return ResponseEntity.ok(
                this.personRepository.findAll()
        );
    }

    @GetMapping("person/{id}")
    public ResponseEntity<Person> getPerson(final @PathVariable("id") Integer id) {
        return ResponseEntity.of(this.personRepository.findById(id));
    }

    @PostMapping("person")
    public ResponseEntity<Person> createPerson(final @RequestBody Person person) {
        final Person savedPerson  = this.personRepository.save(person);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedPerson);
    }

    @DeleteMapping("person/{id}")
    public ResponseEntity<?> deletePerson(final @PathVariable Integer id) {
        if (this.personRepository.findById(id).isPresent()) {
            this.personRepository.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}

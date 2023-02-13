package com.example.restwithspringbootandjavaerudio.service;

import com.example.restwithspringbootandjavaerudio.exceptions.ResourceNotFoundException;
import com.example.restwithspringbootandjavaerudio.model.Person;
import com.example.restwithspringbootandjavaerudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger log = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository personRepository;

    public Person findById(Long id) {
        log.info(String.format("finding one person with id %s...", id));
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No recordes are found for this id!"));
    }

    public List<Person> findAll() {
        log.info(String.format("find a list of persons %s", UUID.randomUUID()));
        return personRepository.findAll();
    }

    public Person create(Person person) {
        log.info("create one person!");
        return personRepository.save(person);
    }

    public Person update(Person person) {
        log.info("updating one person!");

        Person personDB = findById(person.getId());

        personDB.setFirstname(person.getFirstname());
        personDB.setLastname(person.getLastname());
        personDB.setAddress(person.getAddress());
        personDB.setGender(person.getGender());
        personDB.setAddicted(person.isAddicted());

        return personRepository.save(personDB);
    }

    public void delete(Long id) {
        log.info("deleting one person!");

        Person personDB = findById(id);

        personRepository.delete(personDB);

    }

}

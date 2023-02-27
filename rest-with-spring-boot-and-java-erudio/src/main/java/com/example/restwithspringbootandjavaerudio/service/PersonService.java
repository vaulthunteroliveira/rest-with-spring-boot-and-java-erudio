package com.example.restwithspringbootandjavaerudio.service;

import com.example.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
import com.example.restwithspringbootandjavaerudio.exceptions.ResourceNotFoundException;
import com.example.restwithspringbootandjavaerudio.mapper.DozerMapper;
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

    public PersonVO findById(Long id) {
        log.info(String.format("finding one person with id %s...", id));
        Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No recordes are found for this id!"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        log.info(String.format("find a list of persons %s", UUID.randomUUID()));
        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        log.info("create one person!");
        Person entity = personRepository.save(DozerMapper.parseObject(person, Person.class));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO update(PersonVO person) {
        log.info("updating one person!");

        Person personDB = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No recordes are found for this id!"));;

        personDB.setFirstname(person.getFirstname());
        personDB.setLastname(person.getLastname());
        personDB.setAddress(person.getAddress());
        personDB.setGender(person.getGender());
        personDB.setAddicted(person.isAddicted());

        return DozerMapper.parseObject(personRepository.save(personDB), PersonVO.class);
    }

    public void delete(Long id) {
        log.info("deleting one person!");
        PersonVO personDB = findById(id);
        personRepository.delete(DozerMapper.parseObject(personDB, Person.class));

    }

}

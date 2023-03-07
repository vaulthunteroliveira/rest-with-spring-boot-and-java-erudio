package com.example.restwithspringbootandjavaerudio.service;

import com.example.restwithspringbootandjavaerudio.controller.PersonController;
import com.example.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
import com.example.restwithspringbootandjavaerudio.exceptions.ResourceNotFoundException;
import com.example.restwithspringbootandjavaerudio.mapper.DozerMapper;
import com.example.restwithspringbootandjavaerudio.model.Person;
import com.example.restwithspringbootandjavaerudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class PersonService {

    private Logger log = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository personRepository;

    public PersonVO findById(Long id) throws Exception {
        log.info(String.format("finding one person with id %s...", id));
        Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No recordes are found for this id!"));
        PersonVO personVO = DozerMapper.parseObject(entity, PersonVO.class);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personVO;
    }

//    public Page<Person> findAll(Pageable pageable) {
//        log.info(String.format("find a list of persons %s", UUID.randomUUID()));
//        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
//        return personRepository.findAll(pageable);
//    }

    public List<PersonVO> findAll() {
        log.info(String.format("find a list of persons %s", UUID.randomUUID()));
        List<PersonVO> persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);

        persons.forEach(p -> {
            try {
                p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return persons;
    }


    public PersonVO create(PersonVO person) throws Exception {
        log.info("create one person!");
        Person entity = personRepository.save(DozerMapper.parseObject(person, Person.class));
        PersonVO personVO = DozerMapper.parseObject(entity, PersonVO.class);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return personVO;
    }

    public PersonVO update(PersonVO person) throws Exception {
        log.info("updating one person!");

        Person personDB = personRepository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No recordes are found for this id!"));;

        personDB.setFirstname(person.getFirstname());
        personDB.setLastname(person.getLastname());
        personDB.setAddress(person.getAddress());
        personDB.setGender(person.getGender());
        personDB.setArrombado(person.isArrombado());

        PersonVO personVO = DozerMapper.parseObject(personRepository.save(personDB), PersonVO.class);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        return personVO;
    }

    public void delete(Long id) throws Exception {
        log.info("deleting one person!");
        PersonVO personDB = findById(id);
        personRepository.delete(DozerMapper.parseObject(personDB, Person.class));

    }

}

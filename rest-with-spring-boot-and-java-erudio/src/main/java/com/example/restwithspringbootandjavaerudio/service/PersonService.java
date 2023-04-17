package com.example.restwithspringbootandjavaerudio.service;

import com.example.restwithspringbootandjavaerudio.controller.PersonController;
import com.example.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
import com.example.restwithspringbootandjavaerudio.exceptions.RequireObjectIsNullException;
import com.example.restwithspringbootandjavaerudio.exceptions.ResourceNotFoundException;
import com.example.restwithspringbootandjavaerudio.mapper.DozerMapper;
import com.example.restwithspringbootandjavaerudio.model.Person;
import com.example.restwithspringbootandjavaerudio.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final Logger log = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PagedResourcesAssembler<PersonVO> assembler;

    public PersonVO findById(Long id) throws Exception {
        log.info(String.format("finding one person with id %s...", id));
        Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No recordes are found for this id!"));
        PersonVO personVO = DozerMapper.parseObject(entity, PersonVO.class);
        return addHateosToPersonVoList(personVO);
    }

//    public Page<Person> findAll(Pageable pageable) {
//        log.info(String.format("find a list of persons %s", UUID.randomUUID()));
//        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
//        return personRepository.findAll(pageable);
//    }

    public PagedModel<EntityModel<PersonVO>> findAll(Pageable pageable) {

        var personPageList = personRepository.findAll(pageable);

        var personPageVoList = personPageList.map(p -> DozerMapper.parseObject(p, PersonVO.class));

        personPageVoList.map(this::addHateosToPersonVoList);

        return assembler.toModel(personPageVoList,
                linkTo(methodOn(PersonController.class).findAll(pageable.getPageNumber(),
                        pageable.getPageSize(), "asc")).withSelfRel());
    }

    public PagedModel<EntityModel<PersonVO>> findPersonByName(String firstname, Pageable pageable) {

        var personPageList = personRepository.findPersonByName(firstname, pageable);

        var personPageVoList = personPageList.map(p -> DozerMapper.parseObject(p, PersonVO.class));

        personPageVoList.map(this::addHateosToPersonVoList);

        return assembler.toModel(personPageVoList,
                linkTo(methodOn(PersonController.class).findAll(pageable.getPageNumber(),
                        pageable.getPageSize(), "asc")).withSelfRel());
    }

    public PersonVO addHateosToPersonVoList(PersonVO personVO){
        try {
            return personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personVO;
    }


    public PersonVO create(PersonVO person) throws Exception {

        if (person == null) throw new RequireObjectIsNullException();

        log.info("create one person!");
        Person entity = personRepository.save(DozerMapper.parseObject(person, Person.class));
        PersonVO personVO = DozerMapper.parseObject(entity, PersonVO.class);
        return addHateosToPersonVoList(personVO);
    }

    public PersonVO update(PersonVO person) throws Exception {

        if (person == null) throw new RequireObjectIsNullException();

        log.info("updating one person!");

        Person personDB = personRepository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No recordes are found for this id!"));;

        personDB.setFirstname(person.getFirstname());
        personDB.setLastname(person.getLastname());
        personDB.setAddress(person.getAddress());
        personDB.setGender(person.getGender());
        personDB.setArrombado(person.isArrombado());

        PersonVO personVO = DozerMapper.parseObject(personRepository.save(personDB), PersonVO.class);
        return addHateosToPersonVoList(personVO);
    }

    public void delete(Long id) throws Exception {
        log.info("deleting one person!");
        PersonVO personDB = findById(id);
        personRepository.delete(DozerMapper.parseObject(personDB, Person.class));

    }

}

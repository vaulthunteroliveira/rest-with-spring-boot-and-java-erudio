package com.example.restwithspringbootandjavaerudio.service;

import com.example.restwithspringbootandjavaerudio.data.vo.v1.PersonVO;
import com.example.restwithspringbootandjavaerudio.mocks.MockPerson;
import com.example.restwithspringbootandjavaerudio.model.Person;
import com.example.restwithspringbootandjavaerudio.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() throws Exception {
        Person person = input.mockEntity();
        person.setId(1L);
        when(repository.findById(any())).thenReturn(Optional.of(person));
        PersonVO result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test0", result.getAddress());
        assertEquals("First Name Test0", result.getFirstname());
        assertEquals("Last Name Test0", result.getLastname());
        assertEquals("Addres Test0", result.getAddress());
        assertEquals("Male", result.getGender());


    }

    @Test
    void findAll() {
    }

    @Test
    void create() throws Exception {
        Person entity = input.mockEntity();
        Person persisted = entity;
        persisted.setId(1l);

        PersonVO personVO = input.mockVO(1);
        personVO.setKey(1L);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(personVO);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        System.out.println("aooooooo\t"+result.toString());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test0", result.getAddress());
        assertEquals("First Name Test0", result.getFirstname());
        assertEquals("Last Name Test0", result.getLastname());
        assertEquals("Addres Test0", result.getAddress());
        assertEquals("Male", result.getGender());

    }

    @Test
    void update() throws Exception {

        Person entity = input.mockEntity();
        entity.setId(1L);
        Person persisted = entity;

        PersonVO personVO = input.mockVO(1);
        personVO.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(personVO);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstname());
        assertEquals("Last Name Test1", result.getLastname());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void delete() throws Exception {

        Person person = input.mockEntity();
        person.setId(1L);
        when(repository.findById(any())).thenReturn(Optional.of(person));
        service.delete(1L);
    }
}
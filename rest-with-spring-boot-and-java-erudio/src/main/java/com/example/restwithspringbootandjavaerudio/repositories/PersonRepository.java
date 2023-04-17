package com.example.restwithspringbootandjavaerudio.repositories;

import com.example.restwithspringbootandjavaerudio.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE LOWER(p.firstname) LIKE LOWER(CONCAT('%', :firstname, '%'))")
    Page<Person> findPersonByName(@Param("firstname") String firstname, Pageable pageable);
}

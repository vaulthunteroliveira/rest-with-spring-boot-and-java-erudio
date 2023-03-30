package com.example.restwithspringbootandjavaerudio.service;

import com.example.restwithspringbootandjavaerudio.controller.BookController;
import com.example.restwithspringbootandjavaerudio.data.vo.v1.BookVO;
import com.example.restwithspringbootandjavaerudio.exceptions.RequireObjectIsNullException;
import com.example.restwithspringbootandjavaerudio.exceptions.ResourceNotFoundException;
import com.example.restwithspringbootandjavaerudio.mapper.DozerMapper;
import com.example.restwithspringbootandjavaerudio.model.Book;
import com.example.restwithspringbootandjavaerudio.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class BookService {

    private final Logger log = Logger.getLogger(BookService.class.getName());

    @Autowired
    private BookRepository bookRepository;

    public BookVO findById(Long id){
        log.info(String.format("finding one book with id %s...", id));
        Book entity = bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records are found for this id"));
        BookVO bookVO = DozerMapper.parseObject(entity, BookVO.class);
        bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return bookVO;

    }

    public List<BookVO> findAll() {
        log.info(String.format("find a list of books %s", UUID.randomUUID()));

        List<BookVO> books = DozerMapper.parseListObjects(bookRepository.findAll(), BookVO.class);

        books.forEach(b -> {
            b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel());
        });

        return books;
    }

    public BookVO create(BookVO book){
        if (book == null) throw new RequireObjectIsNullException();
        log.info("Creating a book...");
        var entity = DozerMapper.parseObject(book, Book.class);
        var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO book){
        if (book == null) throw new RequireObjectIsNullException();
        log.info("updating a book...");

        var entity = bookRepository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records was found fot this id!"));

        entity.setAuthor(book.getAuthor());
        entity.setTitle(book.getTitle());
        entity.setLauchDate(book.getLauchDate());
        entity.setPrice(book.getPrice());

        var vo = DozerMapper.parseObject(bookRepository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id){
        log.info("Deleting a book...");
        var entity = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No record was found for this id!"));
        "".matches("[0-9]{3}");
        bookRepository.delete(entity);
    }
}

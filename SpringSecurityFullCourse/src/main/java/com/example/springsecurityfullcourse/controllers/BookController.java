package com.example.springsecurityfullcourse.controllers;
import com.example.springsecurityfullcourse.model.Book;
import com.example.springsecurityfullcourse.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/testString")
public class BookController {
    @Autowired
    private BookService bookService;

    //Get Mapping//
    @GetMapping("/text")
    public String hello(){
        return "Hello world";
    }
    //Get data by making objects
    @GetMapping(path= "/getbooks")
    public Book getBooks(){
        Book book = new Book();
        book.setBookId(123);
        book.setBookName("JavaBook");
        book.setAuthorName("Ahmed");
        book.setOpString("Go Study");
        return book;
    }

    //Get all the books
    @GetMapping(path="/allbooks")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> list =this.bookService.getAllBooks();
        if(list.size()<=0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.of(Optional.of(list));
    }

    //Get id
    @GetMapping(path = "user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id){
        Book book = bookService.getBookById(Integer.parseInt(id));
        if(book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    //Get id using name variable
    //Path Variable Name & Id //
    /*@GetMapping(path = "/{name}")
    public Book getBookById(@PathVariable(name="name") String id){
        return this.bookService.getBookById(Integer.parseInt(id));
    }
    //Get name and id
    @GetMapping("/{id}/{name}")
    public Book getBookIDAndName(@PathVariable String id, @PathVariable String name){
        System.out.println("Id: "+id+" "+"Name: "+name);
        return this.bookService.getBookIDAndName(Integer.parseInt(id), name);
    }*/

    //Get only name
     /*@GetMapping("/{name}")
     public Book getBookName( @PathVariable String name){
         System.out.println("Name: "+name);
         return this.bookService.getBookName(name);
     }*/

    //Get name or id
    @GetMapping(path={"/{id}","/{id}/{name}"})
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public Book getBookByIdOrName(@PathVariable ("id") String id, @PathVariable Optional<String> name){
        //System.out.println("Id: "+id+" "+"Name: "+name+" "+"get from optional"+name.get());
        Book b = null;
        if(name.isPresent()){
            b = bookService.getBookIDAndName(Integer.parseInt(id),name.get());
        }else {
            b = bookService.getBookById(Integer.parseInt(id));
        }
        return b;
    }

    @PostMapping(path="/books")
    @PreAuthorize("hasAuthority('student:write')")
    //PostMapping//
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book b = null;
        try{
            b =this.bookService.addBook(book);
            return ResponseEntity.of(Optional.of(b));
        }catch (Exception e){
            System.out.println("Exception"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    //DeleteMapping
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public  ResponseEntity<Void> deleteBook(@PathVariable ("id") int id){
        try {
            this.bookService.deletebook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            System.out.println("Exception "+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //PutMapping
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable ("id") int id){
        try{
            this.bookService.updateBook(book,id);
            return ResponseEntity.ok().body(book);

        }catch(Exception e){
            System.out.println("Exception"+e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}

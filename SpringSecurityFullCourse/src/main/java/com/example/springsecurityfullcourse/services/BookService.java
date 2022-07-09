package com.example.springsecurityfullcourse.services;


import com.example.springsecurityfullcourse.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class BookService {

    public List<Book> list = new ArrayList<Book>();

    //List of books
    public BookService() {
        list.add(new Book(123, "JavaBook", "XYZ", "Hi!"));
        list.add(new Book(124, "PythonBook", "XZ", "Hello!"));
        list.add(new Book(125, "JavaBook", "YZ", "Hey!"));
    }
    public List<Book> getAllBooks() {
        return list;
    }

    //Get data by id
    public Book getBookById(int id) {
        Book b = null;
        try{
            for (Book books : list
            ) {
                if(books.getBookId()==id){
                    b = books;
                    break;
                }
            }
        }catch (Exception e){
            System.out.println("Exception"+e);
        }
        return b;
    }

    //Get data by id and name
    public Book getBookIDAndName(int id, String name){
        Book b = null;
        for (Book books: list
        ) {
            if(books.getBookId()==id && books.getBookName().equalsIgnoreCase(name)){
                b = books;
                break;
            }
        }
        return b;
    }

    //Get data by name
    public Book getBookName(String name){
        Book b = null;
        for (Book books: list
        ) {
            if(books.getBookName().equalsIgnoreCase(name)){
                b = books;
                break;
            }
        }
        return b;
    }

    //New data add
    public Book addBook(Book b){
        list.add(b);
        return b;
    }

    //Delete data
    public void deletebook(int id) {
        list=list.stream().filter(book -> {
            if(book.getBookId()!=id){
                return true;
            }else{
                return false;
            }

        }).collect(Collectors.toList());

    }

    //Update Data
    public void updateBook(Book book,int id) {
        list = list.stream().map(b ->{
            if(b.getBookId() == id){
                b.setBookName(book.getBookName());
                b.setAuthorName(book.getAuthorName());
            }
            return b;
        } ).collect(Collectors.toList());
    }

}
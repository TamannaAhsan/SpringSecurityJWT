package com.example.springsecurityfullcourse.model;
import org.springframework.stereotype.Component;

@Component
public class Book {

    private Integer bookId;
    private String bookName;
    private String authorName;
    private String opString;

    public Book(){
        super();
    }

    public Book(Integer bookId, String bookName, String authorName, String opString){
        super();
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.opString = opString;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getOpString() {
        return opString;
    }

    public void setOpString(String opString) {
        this.opString = opString;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", opString='" + opString + '\'' +
                '}';
    }
}
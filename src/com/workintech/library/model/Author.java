package com.workintech.library.model;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person {
    private List<Book> books;

    // Constructor
    public Author(String name) {
        super(name);
        this.books = new ArrayList<>();
    }

    public Author() {
        super();
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    // Metot: Yeni kitap ekleme
    public void newBook(Book book) {
        books.add(book);
    }


    // Metot: Yazara ait kitapları gösterme
    public List<Book> showBook() {
        return books;
    }

    // Metot: Yazarın kim olduğunu döndürür
    @Override
    public String whoYouAre() {
        return "Ben bir yazarım: " + getName();
    }
}

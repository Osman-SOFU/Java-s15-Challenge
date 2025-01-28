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

    // Metot: Yeni kitap ekleme
    public void newBook(Book book) {
        books.add(book);
        System.out.println(getName() + " adlı yazar yeni bir kitap ekledi: " + book.getTitle());
    }

    // Metot: Yazara ait kitapları gösterme
    public void showBook() {
        if (books.isEmpty()) {
            System.out.println(getName() + " adlı yazarın henüz kitabı yok.");
        } else {
            System.out.println(getName() + " adlı yazarın kitapları:");
            for (Book book : books) {
                book.display();
            }
        }
    }

    // Metot: Yazarın kim olduğunu döndürür
    @Override
    public String whoYouAre() {
        return "Ben bir yazarım: " + getName();
    }
}

package com.workintech.library.model;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    private List<Book> books; // Kullanıcının ödünç aldığı kitaplar
    private int maxBookLimit; // Maksimum ödünç alabileceği kitap sayısı

    // Constructor
    public Reader(String name) {
        super(name);
        this.books = new ArrayList<>();
        this.maxBookLimit = 5; // Varsayılan maksimum kitap limiti
    }

    // Getter ve Setter
    public List<Book> getBooks() {
        return books;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public void setMaxBookLimit(int maxBookLimit) {
        this.maxBookLimit = maxBookLimit;
    }

    // Metod: Kullanıcıya kitap ödünç verme
    public void borrowBook(Book book) {
        if (books.size() >= maxBookLimit) {
            System.out.println(getName() + " maksimum kitap ödünç alma limitine ulaştı!");
            return;
        }
        books.add(book);
        System.out.println(getName() + " adlı kullanıcı " + book.getTitle() + " kitabını ödünç aldı.");
    }

    // Metod: Kullanıcının kitabı iade etmesi
    public void returnBook(Book book) {
        if (books.contains(book)) {
            books.remove(book);
            System.out.println(getName() + " adlı kullanıcı " + book.getTitle() + " kitabını iade etti.");
        } else {
            System.out.println(getName() + " adlı kullanıcı bu kitabı ödünç almamış.");
        }
    }

    // Metod: Kullanıcının ödünç aldığı tüm kitapları gösterir
    public void showBook() {
        if (books.isEmpty()) {
            System.out.println(getName() + " henüz bir kitap ödünç almadı.");
        } else {
            System.out.println(getName() + " adlı kullanıcının ödünç aldığı kitaplar:");
            for (Book book : books) {
                book.display();
            }
        }
    }

    // Metod: Kullanıcının kim olduğunu döndürür
    @Override
    public String whoYouAre() {
        return "Ben bir okuyucuyum: " + getName();
    }
}

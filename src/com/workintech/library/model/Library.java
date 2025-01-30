package com.workintech.library.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Library {
    private List<Book> books;   // Kütüphanedeki kitaplar
    private Set<Reader> readers;  // Kütüphane üyeleri

    // Constructor
    public Library() {
        this.books = new ArrayList<>();
        this.readers = new HashSet<>();
    }

    // Metot: Kütüphanedeki kitapları döndürür
    public List<Book> getBooks() {
        return books;
    }

    // Metot: Kütüphane üyelerini döndürür
    public Set<Reader> getReader() {
        return readers;
    }

    // Metot: Kütüphaneye yeni bir kitap ekler
    public void newBook(Book book) {
        books.add(book);
        System.out.println("Yeni kitap eklendi: " + book.getName());
    }

    // Metot: Kullanıcıya kitap ödünç verme
    public void lendBook(Book book, Reader reader) {
        if (books.contains(book) && !readers.contains(reader)) {
            System.out.println("Hata: Kullanıcı kütüphaneye kayıtlı değil.");
            return;
        }
        if (books.contains(book)) {
            reader.borrowBook(book);
            System.out.println(reader.getName() + " kitabı ödünç aldı: " + book.getName());
        } else {
            System.out.println("Kitap kütüphanede bulunmuyor.");
        }
    }

    // Metot: Kullanıcının kitabı iade etmesi
    public void takeBackBook(Book book, Reader reader) {
        if (readers.contains(reader)) {
            reader.returnBook(book);
            books.add(book);
            System.out.println(reader.getName() + " kitabı iade etti: " + book.getName());
        } else {
            System.out.println("Hata: Kullanıcı kütüphaneye kayıtlı değil.");
        }
    }

    // Metot: Kütüphanedeki tüm kitapları gösterir
    public void showBook() {
        if (books.isEmpty()) {
            System.out.println("Kütüphanede kitap bulunmamaktadır.");
        } else {
            System.out.println("Kütüphanedeki Kitaplar:");
            for (Book book : books) {
                book.display();
            }
        }
    }
}

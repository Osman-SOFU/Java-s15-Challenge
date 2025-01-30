package com.workintech.library.model;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    private List<Book> books; // Kullanıcının ödünç aldığı kitaplar

    // Constructor
    public Reader(String name) {
        super(name);
        this.books = new ArrayList<>();
    }

    public Reader() {
        super();
        this.books = new ArrayList<>(); // ✅ Liste başlatıldı!
    }


    // Getter ve Setter
    public List<Book> getBooks() {
        return books;
    }

    // Metod: Kullanıcının kitabı satın alması
    public void purchaseBook(Book book, Library library) {
        if (library.getBooks().contains(book)) { // Kitap kütüphanede var mı kontrol et
            library.getBooks().remove(book); // Kitabı kütüphane listesinden çıkar
            System.out.println(getName() + " adlı kullanıcı " + book.getName() + " kitabını satın aldı.");
        } else {
            System.out.println("Hata: Bu kitap kütüphanede bulunmamaktadır veya zaten alınmıştır.");
        }
    }


    // Metod: Kullanıcıya kitap ödünç verme
    public void borrowBook(Book book) {
        if (books.size() >= MemberRecord.getMaxBookLimit() && !book.getStatus().equals("Available")) {
            System.out.println(getName() + " maksimum kitap ödünç alma limitine ulaştı!");
            return;
        }
        books.add(book);
        book.updateStatus("Borrowed");
    }

    // Metod: Kullanıcının kitabı iade etmesi
    public void returnBook(Book book) {
        if (books.contains(book)) {
            books.remove(book);
            System.out.println(getName() + " adlı kullanıcı " + book.getName() + " kitabını iade etti.");
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

package com.workintech.library.model;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    private List<Book> books; // Kullanıcının ödünç aldığı kitaplar
    private static Librarian librarian = new Librarian();
    private static MemberRecord memberRecord = new MemberRecord();


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
    public void borrowBook(Book book, Reader reader) {
        // Kitap limit kontrolü
        if (books.size() >= MemberRecord.getMaxBookLimit()) {
            System.out.println(getName() + " maksimum kitap ödünç alma limitine ulaştı! Daha fazla kitap ödünç alamaz.");
            return; // Kitap ödünç alma işlemini iptal et
        }

        // Kitap uygun mu kontrol et (status kontrolü)
        if (!book.getStatus().equals("Alınabilir")) {
            System.out.println(book.getName() + " kitabı şu anda ödünç alınamaz. Durum: " + book.getStatus());
            return; // İşlemi iptal et
        }

        // Kitap ödünç alındıysa ekle
        books.add(book); // Kitabı kullanıcıya ekle
        book.updateStatus("Alınmış"); // Kitap durumunu "Alınmış" olarak işaretle
        System.out.println(getName() + " adlı kullanıcı " + book.getName() + " kitabını ödünç aldı.");
        librarian.createBill(book, reader);
    }

    // Metod: Kullanıcının kitabı iade etmesi
    public void returnBook(Book book, Reader reader) {
        // Kullanıcının kitabı ödünç alıp almadığını kontrol et
        if (!books.contains(book)) {
            System.out.println(getName() + " adlı kullanıcı bu kitabı ödünç almamış.");
            return; // İşlemi iptal et
        }

        // Kitabı kullanıcıdan çıkar ve durumu güncelle
        books.remove(book); // Kitabı kullanıcı listesinden kaldır
        book.updateStatus("Alınabilir"); // Kitap durumunu "Alınabilir" yap
        System.out.println(getName() + " adlı kullanıcı " + book.getName() + " kitabını iade etti.");
        memberRecord.payBill(book, reader);
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

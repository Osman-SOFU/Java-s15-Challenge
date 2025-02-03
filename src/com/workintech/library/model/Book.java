package com.workintech.library.model;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private Long bookID;
    private String author;
    private String name;
    private double price;
    private String status; // Available, Borrowed
    private String edition;
    private LocalDate dateOfPurchase;

    // Constructor
    public Book(Long bookID, String name, String author, double price, String edition, LocalDate dateOfPurchase) {
        this.bookID = bookID;
        this.name = name;
        this.author = author;
        this.price = price;
        this.status = "Alınabilir"; // Varsayılan: Mevcut
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
    }

    public Book() {

    }

    // Metotlar
    public String getName() {
        return name;
    }

    public Long getBookID() {
        return bookID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    // Kitap ödünç alındığında çağrılır
    public void changeOwner() {
        if (this.status.equals("Alınmış")) {
            this.status = "Alınabilir";
        }
    }

    // Kitabın mevcut durumunu döndürür
    public String getOwner() {
        return status.equals("Alınmış") ? "Kitap ödünç alınmış" : "Kitap kütüphanede mevcut";
    }

    // Kitap bilgilerini ekrana yazdırır
    public void display() {
        System.out.println("📚 Kitap Bilgileri 📚");
        System.out.println("ID: " + bookID);
        System.out.println("Başlık: " + name);
        System.out.println("Yazar: " + author);
        System.out.println("Fiyat: " + price + " TL");
        System.out.println("Baskı: " + edition);
        System.out.println("Tarihi: " + dateOfPurchase);
        System.out.println("Durum: " + status);
        System.out.println("---------------------------------");
    }

    // Kitap durumunu günceller
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    // Book sınıfında override edilmiş equals ve hashCode metodları
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookID, book.bookID); // Kitaplar ID'leri ile karşılaştırılır
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID);
    }
}

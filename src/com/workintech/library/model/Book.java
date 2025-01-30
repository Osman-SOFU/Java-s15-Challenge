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
        this.status = "Available"; // Varsayılan: Mevcut
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
    }

    // Metotlar
    public String getName() {
        return name;
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
        if (this.status.equals("Available")) {
            this.status = "Borrowed";
            System.out.println(name + " kitabı ödünç alındı.");
        } else {
            System.out.println(name + " kitabı zaten ödünç alınmış.");
        }
    }

    // Kitabın mevcut durumunu döndürür
    public String getOwner() {
        return status.equals("Borrowed") ? "Kitap ödünç alınmış" : "Kitap kütüphanede mevcut";
    }

    // Kitap bilgilerini ekrana yazdırır
    public void display() {
        System.out.println("📚 Kitap Bilgileri 📚");
        System.out.println("ID: " + bookID);
        System.out.println("Başlık: " + name);
        System.out.println("Yazar: " + author);
        System.out.println("Fiyat: " + price + " TL");
        System.out.println("Baskı: " + edition);
        System.out.println("Satın Alma Tarihi: " + dateOfPurchase);
        System.out.println("Durum: " + status);
        System.out.println("---------------------------------");
    }

    // Kitap durumunu günceller
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }

        Book books = (Book) obj;

        return this.bookID.equals(books.bookID) || this.status.equals((books.status));
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID);
    }
}

package com.workintech.library.model;

public class Librarian extends Person {
    private String password;

    // Constructor
    public Librarian(String name, String password) {
        super(name);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Metot: Kitap arama (Sadece simüle edilen bir çıktı veriyor)
    public void searchBook(String bookTitle, Library library) {
        boolean found = false;
        for (Book book : library.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(bookTitle)) {
                System.out.println("Kitap bulundu: " + book.getTitle());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Kitap bulunamadı: " + bookTitle);
        }
    }

    // Metot: Üyeliği doğrulama (Kütüphane kayıtlarını kontrol eder)
    public void verifyMember(String readerName, Library library) {
        boolean found = false;
        for (Reader reader : library.getReader()) {
            if (reader.getName().equalsIgnoreCase(readerName)) {
                System.out.println("Üyelik doğrulandı: " + reader.getName());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Üye bulunamadı: " + readerName);
        }
    }

    // Metot: Kullanıcıya kitap ödünç verme
    public void issueBook(Book book, Reader reader, Library library) {
        if (library.getBooks().contains(book) && library.getReader().contains(reader)) {
            library.lendBook(book, reader);
            System.out.println(reader.getName() + " adlı kullanıcıya " + book.getTitle() + " kitabı ödünç verildi.");
        } else {
            System.out.println("Hata: Kitap veya üye bulunamadı.");
        }
    }

    // Metot: Gecikme cezası hesaplama
    public double calculateFine(int daysLate) {
        double finePerDay = 2.0; // Gecikme başına günlük ceza
        double totalFine = daysLate * finePerDay;
        System.out.println("Gecikme cezası: " + totalFine + " TL");
        return totalFine;
    }

    // Metot: Fatura oluşturma
    public void createBill(Reader reader, double amount) {
        System.out.println(reader.getName() + " için fatura oluşturuldu. Borç: " + amount + " TL");
    }

    // Metot: Kullanıcının kitabı iade etmesi
    public void returnBook(Book book, Reader reader, Library library) {
        if (library.getReader().contains(reader)) {
            library.takeBackBook(book, reader);
            System.out.println(reader.getName() + " adlı kullanıcı " + book.getTitle() + " kitabını iade etti.");
        } else {
            System.out.println("Hata: Üye bulunamadı.");
        }
    }

    @Override
    public String whoYouAre() {
        return "Ben bir kütüphaneciyim: " + getName();
    }
}

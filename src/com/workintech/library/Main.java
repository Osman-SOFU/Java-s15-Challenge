package com.workintech.library;

import com.workintech.library.model.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();
    private static final Librarian librarian = new Librarian();
    private static final MemberRecord memberRecord = new MemberRecord();



    public static void main(String[] args) {
        System.out.println("📚 Kütüphane Yönetim Sistemine Hoş Geldiniz!");
        int choice;

        // Örnek kullanıcı ve kitap eklenmesi
        initializeSampleData();

        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Satır sonu temizleme

            switch (choice) {
                case 1 -> addBook();
                case 2 -> searchBook();
                case 3 -> updateBook();
                case 4 -> deleteBook();
                case 5 -> listBooks();
                case 6 -> listBooksByAuthor();
                case 7 -> borrowBook();
                case 8 -> returnBook();
                case 9 -> System.out.println("Çıkış yapılıyor...");
                default -> System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
            }
        } while (choice != 9);
    }

    private static void initializeSampleData() {
        Reader exampleReader = new Reader("Osman");
        library.getReader().add(exampleReader);
        library.newBook(new Book(1L, "Java 101", "Ali Yılmaz", 50.0, "1. Baskı", LocalDate.of(2022, 3, 1)));
    }

    private static void displayMenu() {
        System.out.println("\n=== MENÜ ===");
        System.out.println("1. Yeni Kitap Ekle");
        System.out.println("2. Kitap Ara");
        System.out.println("3. Kitap Güncelle");
        System.out.println("4. Kitap Sil");
        System.out.println("5. Kütüphanedeki Kitapları Listele");
        System.out.println("6. Yazara Ait Kitapları Listele");
        System.out.println("7. Kitap Ödünç Al");
        System.out.println("8. Kitap İade Et");
        System.out.println("9. Çıkış");
        System.out.print("Seçiminizi yapın: ");
    }

    private static void addBook() {
        System.out.print("Kitap adı: ");
        String title = scanner.nextLine();
        System.out.print("Yazar: ");
        String author = scanner.nextLine();
        System.out.print("Fiyat: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Buffer temizleme
        System.out.print("Baskı: ");
        String edition = scanner.nextLine();
        System.out.print("Satın Alma Tarihi (YYYY-MM-DD): ");
        LocalDate dateOfPurchase = LocalDate.parse(scanner.nextLine());

        Book newBook = new Book((long) (library.getBooks().size() + 1), title, author, price, edition, dateOfPurchase);
        library.newBook(newBook);
    }

    private static void searchBook() {
        System.out.print("Aranacak kitabın adını girin: ");
        String bookName = scanner.nextLine();
        library.getBooks().stream()
                .filter(b -> b.getName().equals(bookName))
                .forEach(Book::display);
    }

    private static void updateBook() {
        System.out.print("Güncellemek istediğiniz kitabın adını girin: ");
        String bookName = scanner.nextLine();

        Book foundBook = library.getBooks().stream()
                .filter(b -> b.getName().equals(bookName))
                .findFirst()
                .orElse(null);

        if (foundBook != null) {
            System.out.print("Yeni fiyatı girin: ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine();
            foundBook.setPrice(newPrice);
            System.out.print("Yeni durum girin: ");
            String newStatus = scanner.nextLine();
            foundBook.setStatus(newStatus);
            System.out.println("Kitap bilgileri güncellendi.");
        } else {
            System.out.println("Kitap bulunamadı!");
        }
    }

    private static void deleteBook() {
        System.out.print("Silmek istediğiniz kitabın adını girin: ");
        String bookName = scanner.nextLine();

        Book foundBook = library.getBooks().stream()
                .filter(b -> b.getName().equals(bookName))
                .findFirst()
                .orElse(null);

        if (foundBook != null) {
            library.getBooks().remove(foundBook);
            System.out.println("Kitap başarıyla silindi.");
        } else {
            System.out.println("Kitap bulunamadı!");
        }
    }

    private static void listBooks() {
        library.showBook();
    }

    private static void listBooksByAuthor() {
        System.out.print("Yazarın adını girin: ");
        String authorName = scanner.nextLine();
        library.getBooks().stream()
                .filter(b -> b.getAuthor().equals(authorName))
                .forEach(Book::display);
    }

    private static void borrowBook() {
        System.out.print("Ödünç almak istediğiniz kitabın adını girin: ");
        String bookName = scanner.nextLine();
        System.out.print("Üye adınızı girin: ");
        String readerName = scanner.nextLine();

        Book foundBook = library.getBooks().stream()
                .filter(b -> b.getName().equals(bookName))
                .findFirst()
                .orElse(null);

        Reader foundReader = library.getReader().stream()
                .filter(r -> r.getName().equals(readerName))
                .findFirst()
                .orElse(null);

        if (foundBook != null && foundReader != null) {
            library.lendBook(foundBook, foundReader);
            librarian.createBill(foundReader, foundBook);
        } else {
            System.out.println("Kitap veya üye bulunamadı!");
        }
    }

    private static void returnBook() {
        System.out.print("İade etmek istediğiniz kitabın adını girin: ");
        String bookName = scanner.nextLine();
        System.out.print("Üye adınızı girin: ");
        String readerName = scanner.nextLine();

        Book foundBook = library.getBooks().stream()
                .filter(b -> b.getName().equals(bookName))
                .findFirst()
                .orElse(null);

        Reader foundReader = library.getReader().stream()
                .filter(r -> r.getName().equals(readerName))
                .findFirst()
                .orElse(null);

        if (foundBook != null && foundReader != null) {
            librarian.returnBook(foundBook, foundReader, library);
            memberRecord.payBill(foundBook, foundReader);
        } else {
            System.out.println("Kitap veya üye bulunamadı!");
        }
    }
}

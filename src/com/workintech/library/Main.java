package com.workintech.library;

import com.workintech.library.model.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();
    private static final Map<Book, Reader> books = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("📚 Kütüphane Yönetim Sistemine Hoş Geldiniz!");
        int choice;

        // Örnek okuyucu ve kitap eklenmesi
        Reader exampleReader = new Reader("Osman Sofu");
        library.getReader().add(exampleReader);
        library.newBook(new Book(1L, "Java 101", "Ali Yılmaz", 50.0, "1. Baskı", LocalDate.of(2022, 3, 1)));
        library.newBook(new Book(2L, "Java 102", "Ali Yılmaz", 75.0, "2. Baskı", LocalDate.of(2023, 4, 2)));
        library.newBook(new Book(3L, "Java 103", "Veli Meli", 25.0, "3. Baskı", LocalDate.of(2024, 5, 3)));
        library.newBook(new Book(4L, "Java 104", "Mehmet Yıldız", 100.0, "4. Baskı", LocalDate.of(2020, 6, 4)));
        library.newBook(new Book(5L, "Java 105", "Ahmet Sofu", 150.0, "5. Baskı", LocalDate.of(2021, 7, 5)));

        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Satır sonu temizleme

            switch (choice) {
                case 1 -> addNewBook();
                case 2 -> searchBook();
                case 3 -> updateBook();
                case 4 -> deleteBook();
                case 5 -> listBooksByCategory();
                case 6 -> listBooksByAuthor();
                case 7 -> borrowBook();
                case 8 -> returnBook();
                case 9 -> System.out.println("Çıkış yapılıyor...");
                default -> System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
            }
        } while (choice != 9);
    }

    private static void displayMenu() {
        System.out.println("\n=== MENÜ ===");
        System.out.println("1. Yeni Kitap Ekle");
        System.out.println("2. Kitap Ara");
        System.out.println("3. Kitap Güncelle");
        System.out.println("4. Kitap Sil");
        System.out.println("5. Kategorideki Kitapları Listele");
        System.out.println("6. Yazara Ait Kitapları Listele");
        System.out.println("7. Kitap Ödünç Al");
        System.out.println("8. Kitap İade Et");
        System.out.println("9. Çıkış");
        System.out.print("Seçiminizi yapın: ");
    }

    private static void addNewBook() {
        System.out.print("Kitap ID: ");
        Long id = Long.valueOf(scanner.nextInt());
        scanner.nextLine();

        System.out.print("Kitap Adı: ");
        String title = scanner.nextLine();

        System.out.print("Yazar: ");
        String author = scanner.nextLine();

        System.out.print("Fiyat: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Baskı: ");
        String edition = scanner.nextLine();

        System.out.print("Satın Alma Tarihi (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        LocalDate purchaseDate = LocalDate.parse(date);

        Book book = new Book(id, title, author, price, edition, purchaseDate);
        library.newBook(book);
        System.out.println("Kitap başarıyla eklendi!");
    }

    private static void searchBook() {
        System.out.print("Kitap Adı veya Yazar Adı ile arama yapın: ");
        String searchQuery = scanner.nextLine();
        boolean found = false;

        for (Book book : library.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(searchQuery) || book.getAuthor().equalsIgnoreCase(searchQuery)) {
                book.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Kitap bulunamadı.");
        }
    }

    private static void updateBook() {
        System.out.print("Güncellenecek Kitap ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Book book = library.getBooks().stream()
                .filter(b -> b.getBookID() == id)
                .findFirst()
                .orElse(null);

        if (book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }

        System.out.print("Yeni Kitap Durumu (Available/Borrowed): ");
        String newStatus = scanner.nextLine();
        book.updateStatus(newStatus);
        System.out.println("Kitap durumu başarıyla güncellendi.");
    }

    private static void deleteBook() {
        System.out.print("Silinecek Kitap ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = library.getBooks().removeIf(book -> book.getBookID() == id);
        if (removed) {
            System.out.println("Kitap başarıyla silindi.");
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    private static void listBooksByCategory() {
        System.out.println("Kategorideki Kitaplar:");
        library.showBook();
    }

    private static void listBooksByAuthor() {
        System.out.print("Yazar Adı: ");
        String authorName = scanner.nextLine();
        boolean found = false;

        for (Book book : library.getBooks()) {
            if (book.getAuthor().equalsIgnoreCase(authorName)) {
                book.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Bu yazara ait kitap bulunamadı.");
        }
    }

    private static void borrowBook() {
        System.out.print("Kitap ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Okuyucu Adı: ");
        String readerName = scanner.nextLine();

        Reader reader = library.getReader().stream()
                .filter(r -> r.getName().equalsIgnoreCase(readerName))
                .findFirst()
                .orElse(null);

        if (reader == null) {
            System.out.println("Okuyucu kütüphaneye kayıtlı değil.");
            return;
        }

        Book book = library.getBooks().stream()
                .filter(b -> b.getBookID() == bookId)
                .findFirst()
                .orElse(null);

        if (book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }

        if (books.containsKey(book)) {
            System.out.println("Kitap zaten ödünç alınmış.");
        } else {
            books.put(book, reader);
            library.lendBook(book, reader);
            System.out.println(reader.getName() + " adlı kullanıcıya " + book.getTitle() + " kitabı ödünç verildi.");
        }
    }

    private static void returnBook() {
        System.out.print("Kitap ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Okuyucu Adı: ");
        String readerName = scanner.nextLine();

        Book book = books.keySet().stream()
                .filter(b -> b.getBookID() == bookId)
                .findFirst()
                .orElse(null);

        if (book == null) {
            System.out.println("Bu kitap ödünç alınmamış.");
            return;
        }

        Reader reader = books.get(book);
        if (!reader.getName().equalsIgnoreCase(readerName)) {
            System.out.println("Bu kullanıcı kitabı ödünç almamış.");
            return;
        }

        books.remove(book);
        library.takeBackBook(book, reader);
        System.out.println(reader.getName() + " adlı kullanıcı " + book.getTitle() + " kitabını iade etti.");
    }
}

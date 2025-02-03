package com.workintech.library;

import com.workintech.library.model.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();
    private static final Map<Book, Reader> borrowedBooks = new HashMap<>();


    public static void main(String[] args) {
        boolean isRunning = true; // Uygulamanın çalışıp çalışmadığını kontrol eden değişken

        try {
            System.out.println("📚 Kütüphane Yönetim Sistemine Hoş Geldiniz!");

            // Örnek kullanıcı ve kitap eklenmesi
            initializeSampleData();

            while (isRunning) { // Döngüyü kontrol eden değişken kullanılıyor
                displayMenu();
                try {
                    System.out.print("Seçiminizi yapın: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Satır sonu temizleme

                    // Kullanıcının seçimine göre işlem yapılır
                    switch (choice) {
                        case 1 -> addBook();
                        case 2 -> searchBook();
                        case 3 -> updateBook();
                        case 4 -> deleteBook();
                        case 5 -> listBooks();
                        case 6 -> listBooksByAuthor();
                        case 7 -> borrowBook();
                        case 8 -> returnBook();
                        case 9 -> addReader();
                        case 10 -> {
                            System.out.println("Uygulamadan çıkılıyor...");
                            isRunning = false; // Döngü sonlanmasını sağlayan kontrol
                        }
                        default -> System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Hata: Geçerli bir seçim yapınız (Sayı girin).");
                    scanner.nextLine(); // Hatalı girişle birlikte buffer temizlenir
                }
            }
        } catch (Exception e) {
            System.out.println("Uygulamada bir hata meydana geldi: " + e.getMessage());
            e.printStackTrace(); // Hatanın detaylarını konsola yazdırır (Opsiyonel)
        } finally {
            System.out.println("Kütüphane yönetim sistemi kapatılıyor...");
            scanner.close(); // Scanner kaynağını kapatıyoruz
        }
    }

    private static void initializeSampleData() {
        // Örnek Kullanıcılar
        library.getReader().add(new Reader("Osman"));
        library.getReader().add(new Reader("Hakkı"));
        library.getReader().add(new Reader("Burak"));

        // Örnek Üyeler
        MemberRecord member1 = new MemberRecord(1L, Type.STUDENT, "Osman", "Kütahya", "555-555-5555", 5);
        MemberRecord member2 = new MemberRecord(2L, Type.FACULTY, "Hakkı", "İstanbul", "555-666-7777", 5);
        MemberRecord member3 = new MemberRecord(3L, Type.STUDENT, "Burak", "Ankara", "555-777-8888", 5);
        member1.getMember(); // Detayları göster (isteğe bağlı)
        member2.getMember();
        member3.getMember();

        // Örnek Kitaplar
        library.newBook(new StudyBooks(1L, "101", "Ali", 50.0, "1. Baskı", LocalDate.of(2021, 3, 1)));
        library.newBook(new Journals(2L, "102", "Yılmaz", 75.0, "2. Baskı", LocalDate.of(2022, 4, 2)));
        library.newBook(new Magazines(3L, "103", "Mehmet", 100.0, "3. Baskı", LocalDate.of(2023, 5, 3)));
        library.newBook(new StudyBooks(4L, "104", "Veli", 125.0, "4. Baskı", LocalDate.of(2024, 6, 4)));
        library.newBook(new Journals(5L, "105", "Veli", 150.0, "5. Baskı", LocalDate.of(2021, 7, 5)));
        library.newBook(new Magazines(6L, "106", "Hasan", 175.0, "6. Baskı", LocalDate.of(2022, 8, 6)));
        library.newBook(new StudyBooks(7L, "107", "Ayşe", 200.0, "7. Baskı", LocalDate.of(2023, 9, 7)));

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
        System.out.println("9. Yeni Okuyucu Ekle"); // Eklendi
        System.out.println("10. Çıkış");
        System.out.print("Seçiminizi yapın: ");
    }

    private static void addReader() {
        System.out.println("Yeni üye bilgilerini giriniz:");

        System.out.print("Üye Adı: ");
        String name = scanner.nextLine();

        System.out.print("Adres: ");
        String address = scanner.nextLine();

        System.out.print("Telefon Numarası: ");
        String phoneNo = scanner.nextLine();

        System.out.print("Üye Türü (1: Öğrenci, 2: Fakülte): ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine(); // Buffer temizleme

        Type type = (typeChoice == 1) ? Type.STUDENT : Type.FACULTY;

        // Üye bilgilerini kaydet
        MemberRecord newMember = new MemberRecord(
                (long) (library.getReader().size() + 1), // ID otomatik oluşturulur
                type,
                name,
                address,
                phoneNo,
                MemberRecord.getMaxBookLimit()
        );

        // Library'ye bu kullanıcıyı ekle
        library.getReader().add(new Reader(name));
        System.out.println(name + " adlı kullanıcı sisteme üye olarak eklendi.");
        newMember.getMember(); // Üye detaylarını göster (isteğe bağlı)
    }

    private static void addBook() {
        System.out.println("Lütfen eklemek istediğiniz kitap türünü seçin:");
        System.out.println("1. StudyBooks");
        System.out.println("2. Journals");
        System.out.println("3. Magazines");

        int categoryChoice = scanner.nextInt();
        scanner.nextLine(); // Buffer temizleme

        // Kullanıcı bilgilerini giriyor
        System.out.print("Kitap adı: ");
        String title = scanner.nextLine();
        System.out.print("Yazar: ");
        String author = scanner.nextLine();
        System.out.print("Fiyat: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Buffer temizleme
        System.out.print("Baskı: ");
        String edition = scanner.nextLine();
        System.out.print("Tarih (YYYY-MM-DD): ");
        LocalDate dateOfPurchase = LocalDate.parse(scanner.nextLine());

        // Kullanıcı seçimine göre kitap ekleme
        switch (categoryChoice) {
            case 1 -> {
                StudyBooks studyBook = new StudyBooks(
                        (long) (library.getBooks().size() + 1),
                        title, author, price, edition, dateOfPurchase);
                library.newBook(studyBook);
            }
            case 2 -> {
                Journals journal = new Journals(
                        (long) (library.getBooks().size() + 1),
                        title, author, price, edition, dateOfPurchase);
                library.newBook(journal);
            }
            case 3 -> {
                Magazines magazine = new Magazines(
                        (long) (library.getBooks().size() + 1),
                        title, author, price, edition, dateOfPurchase);
                library.newBook(magazine);
            }
            default -> System.out.println("Geçersiz seçim! Kitap eklenemedi.");
        }
    }

    private static void searchBook() {
        System.out.println("Arama ölçütü (1: ID, 2: Ad, 3: Yazar): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.print("Kitap ID: ");
            Long id = scanner.nextLong();
            library.getBooks().stream()
                    .filter(b -> b.getBookID().equals(id))
                    .forEach(Book::display);
        } else if (choice == 2) {
            System.out.print("Kitap Adı: ");
            String bookName = scanner.nextLine();
            library.getBooks().stream()
                    .filter(b -> b.getName().equals(bookName))
                    .forEach(Book::display);
        } else if (choice == 3) {
            System.out.print("Yazar Adı: ");
            String authorName = scanner.nextLine();
            library.getBooks().stream()
                    .filter(b -> b.getAuthor().equals(authorName))
                    .forEach(Book::display);
        }
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
        String bookName = scanner.nextLine().trim(); // Kullanıcı girdisini düzenleme

        // Kitabı ad üzerinden buluyoruz
        Book foundBook = library.getBooks().stream()
                .filter(b -> b.getName().equalsIgnoreCase(bookName)) // Büyük-küçük harf duyarlılığı yok
                .findFirst()
                .orElse(null);

        if (foundBook != null) {
            boolean isRemoved = library.deleteBook(foundBook); // `deleteBook` çağırılır
            if (isRemoved) {
                System.out.println("Kitap silindi.");
            } else {
                System.out.println("Kitap silinemedi, lütfen kontrol edin.");
            }
        } else {
            System.out.println(bookName + " adlı kitap bulunamadı.");
        }

        // Silme sonrası listeyi kontrol ediyoruz
        System.out.println("\nGüncel Kütüphanedeki Kitaplar:");
        library.getBooks().forEach(book -> System.out.println(book.getName()));
    }

    private static void listBooks() {
        System.out.println("Hangi türdeki kitapları görmek istiyorsunuz:");
        System.out.println("1. StudyBooks");
        System.out.println("2. Journals");
        System.out.println("3. Magazines");
        System.out.println("4. Tüm Kitaplar");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Satır sonu temizleme

            switch (choice) {
                case 1 -> {
                    if (library.listBooksByCategory(StudyBooks.class)) {
                        System.out.println("StudyBooks türünde kitap bulunmamaktadır.");
                    } else {
                        library.listBooksByCategory(StudyBooks.class);
                    }
                }

                case 2 -> {
                    if (library.listBooksByCategory(Journals.class)) {
                        System.out.println("Journals türünde kitap bulunmamaktadır.");
                    } else {
                        library.listBooksByCategory(Journals.class);
                    }
                }

                case 3 -> {
                    if (library.listBooksByCategory(Magazines.class)) {
                        System.out.println("Magazines türünde kitap bulunmamaktadır.");
                    } else {
                        library.listBooksByCategory(Magazines.class);
                    }
                }

                case 4 -> {
                    if (library.getBooks().isEmpty()) {
                        System.out.println("Aktif olarak kütüphanede hiç kitap bulunmamaktadır.");
                    } else {
                        library.showBook(); // Tüm kitapları listeleme
                    }
                }

                default -> System.out.println("Geçersiz seçim! (1 - 4 arasında bir değer giriniz)");
            }
        } catch (InputMismatchException e) {
            System.out.println("Hata: Geçersiz bir giriş yaptınız! Lütfen yalnızca sayısal bir değer girin.");
            scanner.nextLine(); // Hatalı giriş sonrası buffer temizleme
        }
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

        // Kitap ve üye bilgilerini bul
        Book foundBook = library.getBooks().stream()
                .filter(b -> b.getName().equalsIgnoreCase(bookName))
                .findFirst().orElse(null);

        Reader foundReader = library.getReader().stream()
                .filter(r -> r.getName().equalsIgnoreCase(readerName))
                .findFirst().orElse(null);

        if (foundBook != null && foundReader != null) {
            String bookStatus = foundBook.getOwner(); // Kitap durumunu kontrol et
            if (bookStatus.equals("Kitap ödünç alınmış")) {
                System.out.println("Hata: " + bookName + " kitabı şu an ödünç alınmış.");
            } else {
                // Ödünç alma işlemleri
                borrowedBooks.put(foundBook, foundReader);     // Kitap ödünç alınan listeye eklenir
                library.lendBook(foundBook, foundReader);      // Kütüphane kaydına işlenir
                foundBook.getOwner(); // Kitap durumunu "Alınamaz" yap

                // MemberRecord işlemleri (ödünç alınan kitap sayısını artırır)
                MemberRecord memberRecord = new MemberRecord(
                        (long) foundReader.getBooks().size(),
                        MemberRecord.getType(),
                        foundReader.getName(),
                        MemberRecord.getAddress(),
                        MemberRecord.getPhoneNo(),
                        MemberRecord.getMaxBookLimit()
                );

                // Kullanıcının aldığı kitapları göster
                System.out.println("\n=== Ödünç Alınan Kitaplar ===");
                foundReader.showBook(); // Kullanıcının şu anda aldığı kitapları listele
            }
        } else {
            System.out.println("Hata: Kitap bulunamadı veya üye geçersiz!");
        }
    }

    private static void returnBook() {
        System.out.print("İade etmek istediğiniz kitabın adını girin: ");
        String bookName = scanner.nextLine();
        System.out.print("Üye adınızı girin: ");
        String readerName = scanner.nextLine();

        // Kitap ve üye bilgilerini bul
        Book foundBook = borrowedBooks.keySet().stream()
                .filter(b -> b.getName().equalsIgnoreCase(bookName))
                .findFirst().orElse(null);

        Reader foundReader = library.getReader().stream()
                .filter(r -> r.getName().equalsIgnoreCase(readerName))
                .findFirst().orElse(null);

        if (foundBook != null && foundReader != null) {
            String bookStatus = foundBook.getOwner(); // Kitap durumunu kontrol et
            if (bookStatus.equals("Kitap kütüphanede mevcut")) {
                System.out.println("Hata: " + bookName + " adlı kitap zaten kütüphanede mevcut!");
            } else {
                // İade işlemleri
                borrowedBooks.remove(foundBook);  // İade edilen kitabı ödünç listesinden kaldır
                library.takeBackBook(foundBook, foundReader); // Kütüphane işlemi
                foundBook.changeOwner();         // Kitap durumu "Alınabilir" yapılır

                // MemberRecord işlemleri (ödünç edilen kitap sayısını azaltır)
                MemberRecord memberRecord = new MemberRecord(
                        (long) foundReader.getBooks().size(),
                        MemberRecord.getType(),
                        foundReader.getName(),
                        MemberRecord.getAddress(),
                        MemberRecord.getPhoneNo(),
                        MemberRecord.getMaxBookLimit()
                );

                // Kullanıcının aldığı kitapları göster
                System.out.println("\n=== Ödünç Alınan Kitaplar ===");
                foundReader.showBook(); // Kullanıcının şu anda aldığı kitapları listele
            }
        } else {
            System.out.println("Hata: Kitap veya kullanıcı bilgisi hatalı ya da iade edilemez!");
        }
    }
}

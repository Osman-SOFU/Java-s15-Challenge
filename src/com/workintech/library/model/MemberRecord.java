package com.workintech.library.model;

import java.time.LocalDate;

public class MemberRecord {
    private Long memberId;
    private static Type type; // "Student" veya "Faculty"
    private LocalDate dateOfMembership;
    private int noBooksIssued;
    private static int maxBookLimit = 5;
    private static String name;
    private static String address;
    private static String phoneNo;

    // Constructor
    public MemberRecord(Long memberId, Type type, String name, String address, String phoneNo, int maxBookLimit) {
        this.memberId = memberId;
        this.type = type;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.dateOfMembership = LocalDate.now(); // Üyelik başlangıç tarihi otomatik olarak atanır
        this.noBooksIssued = 0; // Başlangıçta kitap ödünç alınmamış
        this.maxBookLimit = 5;
    }

    public MemberRecord() {

    }

    public static String getName() {
        return name;
    }

    public static String getAddress() {
        return address;
    }

    public static String getPhoneNo() {
        return phoneNo;
    }

    public static Type getType() {
        return type;
    }

    public static int getMaxBookLimit() {
        return maxBookLimit;
    }

    // Metot: Üye bilgilerini döndürür
    public void getMember() {
        System.out.println( "Üye ID: " + memberId +
                ", İsim: " + name +
                ", Tür: " + type +
                ", Üyelik Tarihi: " + dateOfMembership +
                ", Adres: " + address +
                ", Telefon: " + phoneNo +
                ", Ödünç Alınan Kitap Sayısı: " + noBooksIssued +
                ", Maksimum Kitap Limiti: " + 5);
    }

    // Metot: Ödünç alınan kitap sayısını artırır
    public void incBookIssued() {
        if (noBooksIssued < maxBookLimit) {
            noBooksIssued++;
            System.out.println(name + " adlı üyenin ödünç aldığı kitap sayısı: " + noBooksIssued);
        } else {
            System.out.println(name + " maksimum kitap ödünç alma limitine ulaştı!");
        }
    }

    // Metot: Ödünç alınan kitap sayısını azaltır
    public void decBookIssued() {
        if (noBooksIssued > 0) {
            noBooksIssued--;
            System.out.println(name + " adlı üyenin ödünç aldığı kitap sayısı: " + noBooksIssued);
        } else {
            System.out.println(name + " adlı üyenin ödünç aldığı kitap bulunmamaktadır.");
        }
    }

    // Metot: Üyenin faturasının iadesi
    public void payBill(Book book, Reader reader) {
        System.out.println(reader.getName() + " adlı üyeye " + book.getPrice() + " TL fatura iadesi yapıldı.");
    }
}

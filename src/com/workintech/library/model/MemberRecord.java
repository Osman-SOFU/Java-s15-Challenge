package com.workintech.library.model;

import java.time.LocalDate;

public class MemberRecord {
    private Long memberId;
    private Type type; // "Student" veya "Faculty"
    private LocalDate dateOfMembership;
    private int noBooksIssued;
    private static int maxBookLimit = 5;
    private String name;
    private String address;
    private String phoneNo;

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

    public Type getType() {
        return type;
    }

    public static int getMaxBookLimit() {
        return maxBookLimit;
    }

    // Metot: Üye bilgilerini döndürür
    public String getMember() {
        return "Üye ID: " + memberId +
                ", İsim: " + name +
                ", Tür: " + type +
                ", Üyelik Tarihi: " + dateOfMembership +
                ", Adres: " + address +
                ", Telefon: " + phoneNo +
                ", Ödünç Alınan Kitap Sayısı: " + noBooksIssued +
                ", Maksimum Kitap Limiti: " + 5;
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
        System.out.println(reader.getName() + " adlı üyeye " + book.getPrice() + " TL fatura bedeli edildi.");
    }
}

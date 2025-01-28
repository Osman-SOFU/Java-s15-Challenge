package com.workintech.library.model;

import java.time.LocalDate;

public class MemberRecord {
    private int memberId;
    private String type; // "Student" veya "Faculty"
    private LocalDate dateOfMembership;
    private int noBooksIssued;
    private int maxBookLimit;
    private String name;
    private String address;
    private String phoneNo;

    // Constructor
    public MemberRecord(int memberId, String type, String name, String address, String phoneNo, int maxBookLimit) {
        this.memberId = memberId;
        this.type = type;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.dateOfMembership = LocalDate.now(); // Üyelik başlangıç tarihi otomatik olarak atanır
        this.noBooksIssued = 0; // Başlangıçta kitap ödünç alınmamış
        this.maxBookLimit = maxBookLimit;
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
                ", Maksimum Kitap Limiti: " + maxBookLimit;
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

    // Metot: Üyenin fatura borcunu ödemesini sağlar
    public void payBill(double amount) {
        System.out.println(name + " adlı üye " + amount + " TL fatura ödedi.");
    }
}

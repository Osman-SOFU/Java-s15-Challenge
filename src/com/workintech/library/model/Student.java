package com.workintech.library.model;

public class Student extends MemberRecord {
    // Constructor
    public Student(Long memberId, String name, String address, String phoneNo) {
        super(memberId, "Student", name, address, phoneNo, 5); // Öğrenciler için maksimum 5 kitap limiti
    }
}

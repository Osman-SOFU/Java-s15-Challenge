package com.workintech.library.model;

public class Faculty extends MemberRecord {
    // Constructor
    public Faculty(Long memberId, String name, String address, String phoneNo) {
        super(memberId, "Faculty", name, address, phoneNo, 10); // Fakülte üyeleri için maksimum 10 kitap limiti
    }
}

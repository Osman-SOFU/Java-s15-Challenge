package com.workintech.library.model;


import java.time.LocalDate;

public class StudyBooks extends Book {
    public StudyBooks(Long bookID, String name, String author, double price, String edition, LocalDate dateOfPurchase) {
        super(bookID, name, author, price, edition, dateOfPurchase);
    }
}

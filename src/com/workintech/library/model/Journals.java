package com.workintech.library.model;

import java.time.LocalDate;

public class Journals extends Book {
    // Constructor
    public Journals(Long bookID, String name, String author, double price, String edition, LocalDate dateOfPurchase) {
        super(bookID, name, author, price, edition, dateOfPurchase);
    }
}

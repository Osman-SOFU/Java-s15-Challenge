package com.workintech.library.model;

import java.time.LocalDate;

public class Magazines extends Book {
    // Constructor
    public Magazines(int bookID, String name, String author, double price, String edition, LocalDate dateOfPurchase) {
        super(bookID, name, author, price, edition, dateOfPurchase);
    }
}

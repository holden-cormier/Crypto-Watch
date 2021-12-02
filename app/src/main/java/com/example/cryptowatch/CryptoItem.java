package com.example.cryptowatch;

import android.media.Image;

import java.util.LinkedList;

// Class to store a crypto item's data
public class CryptoItem {
    public String name;
    public float price;
    public String symbol;
    public String imageURL;



    public CryptoItem(String name, float price, String symbol, String imageURL) {

        this.name = name;
        this.price = price;
        this.symbol = symbol;
        this.imageURL = imageURL;
    }
}

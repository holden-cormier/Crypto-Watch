package com.example.cryptowatch;

import android.media.Image;

import java.util.LinkedList;

public class CryptoItem {
    public String name;
    public float price;
    public String symbol;


    public CryptoItem(String name, float price, String symbol) {
        this.name = name;
        this.price = price;
        this.symbol = symbol;
    }
}

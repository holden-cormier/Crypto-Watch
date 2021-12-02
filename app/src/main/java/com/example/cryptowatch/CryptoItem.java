package com.example.cryptowatch;

import android.media.Image;

import java.util.LinkedList;

// Class to store a crypto item's data
public class CryptoItem {
    public String name;
    public float price;
    public String symbol;
    public String imageURL;
    public float percentChange;
    public float marketCap;
    public float highestPrice;
    public float lowestPrice;
    public float circulatingSupply;
    public float totalSupply;


    public CryptoItem(String name, float price, String symbol, String imageURL, float percentChange, float marketCap, float highestPrice, float lowestPrice, float circulatingSupply, float totalSupply) {
        this.name = name;
        this.price = price;
        this.symbol = symbol;
        this.imageURL = imageURL;
        this.percentChange = percentChange;
        this.marketCap = marketCap;
        this.highestPrice = highestPrice;
        this.lowestPrice = lowestPrice;
        this.circulatingSupply = circulatingSupply;
        this.totalSupply = totalSupply;
    }
}

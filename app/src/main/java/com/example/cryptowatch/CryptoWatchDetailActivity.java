package com.example.cryptowatch;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class CryptoWatchDetailActivity extends AppCompatActivity {

    private CryptoItem cryptoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_detail);
        cryptoItem = (CryptoItem) getIntent().getSerializableExtra("CryptoItem");
        displayParameters();
    }

    private void displayParameters() {
        TextView cryptoName = findViewById(R.id.CryptoNameTextView);
        TextView symbolTextView = findViewById(R.id.SymbolTextView);
        TextView priceView = findViewById(R.id.PriceTextView);
        TextView highPriceView = findViewById(R.id.HighPriceTextView);
        TextView lowPriceView = findViewById(R.id.LowestPriceTextView);
        TextView percentChangeView = findViewById(R.id.PercentChangeTextView);
        TextView marketCapTextView = findViewById(R.id.MarketCapTextView);
        TextView circulatingSupplyView = findViewById(R.id.CirculatingSupplyTextView);
        TextView totalVolumeView = findViewById(R.id.TotalVolumeTextView);
        ImageView graphImageView = findViewById(R.id.GraphImageView);
        cryptoName.setText(cryptoItem.name);
        symbolTextView.setText(cryptoItem.symbol);
        priceView.setText(Float.toString(cryptoItem.price));
        highPriceView.setText(Float.toString(cryptoItem.highestPrice));
        lowPriceView.setText(Float.toString(cryptoItem.lowestPrice));
        percentChangeView.setText(Float.toString(cryptoItem.percentChange));
        marketCapTextView.setText(Float.toString(cryptoItem.marketCap));
        circulatingSupplyView.setText(Float.toString(cryptoItem.circulatingSupply));
        totalVolumeView.setText(Float.toString(cryptoItem.totalVolume));
        Picasso.get().load(cryptoItem.imageURL).into( graphImageView);

    }
}

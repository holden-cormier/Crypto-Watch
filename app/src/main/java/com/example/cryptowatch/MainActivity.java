package com.example.cryptowatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public final String LOG_TAG = "CRYPTO-WATCH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FetchCryptoData fetchCryptoData = new FetchCryptoData(this);
        fetchCryptoData.execute();
    }

    private class FetchCryptoData extends AsyncTask<Void, Void, CryptoItem> {
        Context context;

        public FetchCryptoData(Context context) {
            this.context = context;
        }

        protected CryptoItem doInBackground(Void... unused) {
            CryptoItem result;
            result = new CryptoItem("Ethereum", 4500f, "eth");
            return result;
        }

        protected void onPostExecute(CryptoItem result) {
            // Do something with the result.
            handleCryptoDataResult(result);
        }
    }

    private void handleCryptoDataResult(CryptoItem result) {
        Log.d("CRYPTO-WATCH", result.name);
    }
}
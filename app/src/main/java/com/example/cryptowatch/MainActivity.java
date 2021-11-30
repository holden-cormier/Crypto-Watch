package com.example.cryptowatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

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

        private List<CryptoItem> getCryptoList() throws IOException {
            //Google Books API URL
            String apiURL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false";

            //Make connection to API
            URL requestURL = new URL(apiURL);
            HttpURLConnection urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Receive the response
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            //Create a String with the reponse
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
            String jsonString = builder.toString();
            Log.d("CRYPTO-WATCH", jsonString);
            return new LinkedList<>();
        }

        protected CryptoItem doInBackground(Void... unused) {
            CryptoItem result;
            try {
                getCryptoList();
            } catch (IOException e) {
                Log.d("CRYPTO-WATCH", e.toString());
            }
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
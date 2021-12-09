package com.example.cryptowatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    public final String LOG_TAG = "CRYPTO-WATCH";
    private LinkedList<CryptoItem> CryptoList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private CryptoWatchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Call the fetch data class
        FetchCryptoData fetchCryptoData = new FetchCryptoData(this);
        fetchCryptoData.execute();
    }


    // Class for fetching crypto data asynchronously
    private class FetchCryptoData extends AsyncTask<Void, Void, LinkedList<CryptoItem>> {
        Context context;

        public FetchCryptoData(Context context) {
            this.context = context;
        }

        private LinkedList<CryptoItem> getCryptoList() throws IOException {
            // URL to CoinGecko
            String apiURL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false";

            // Connect to the URL
            URL requestURL = new URL(apiURL);
            HttpURLConnection urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Parse response
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Convert response to string
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            String jsonString = builder.toString();

            // Now convert string to JSON so that we may parse it into an array
            JSONArray cryptosListArray;
            LinkedList<CryptoItem> cryptoItems = new LinkedList<>();
            try {
                cryptosListArray = new JSONArray(jsonString);
                final int n = cryptosListArray.length();
                for (int i = 0; i < n; i++) {
                    final JSONObject cryptoItem = cryptosListArray.getJSONObject(i);
                    cryptoItems.push(new CryptoItem(
                            cryptoItem.getString("name"),
                            (float) (cryptoItem.getDouble("current_price")),
                            cryptoItem.getString("symbol"),
                            cryptoItem.getString("image"),
                            (float) (cryptoItem.getDouble("price_change_percentage_24h")),
                            (float) (cryptoItem.getDouble("market_cap")),
                            (float) (cryptoItem.getDouble("ath")),
                            (float) (cryptoItem.getDouble("atl")),
                            (float) (cryptoItem.getDouble("circulating_supply")),
                            (float) (cryptoItem.getDouble("total_volume"))
                    ));
                }
            } catch (Exception e) {
                Log.d("CRYPTO-WATCH", "Error converting to json");
                Log.d("CRYPTO-WATCH", e.getMessage());
            }
            return cryptoItems;
        }

        // To handle it asynchronously, prevent the main thread from being blocked
        protected LinkedList<CryptoItem> doInBackground(Void... unused) {
            LinkedList<CryptoItem> result = new LinkedList<>();
            try {
                result = getCryptoList();
            } catch (IOException e) {
                Log.d("CRYPTO-WATCH", e.toString());
            }
            return result;
        }

        // Call the function to process the data by setting the UI
        protected void onPostExecute(LinkedList<CryptoItem> result) {
            // Do something with the result.
            handleCryptoDataResult(result);
        }
    }

    // This takes in the crypto data from the API and updates the UI
    private void handleCryptoDataResult(LinkedList<CryptoItem> result) {
        this.CryptoList = result;
        // Initialize the recycler view and the adapter with the data
        mRecyclerView = findViewById(R.id.recycelerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CryptoWatchAdapter(this,result);
        mRecyclerView.setAdapter(mAdapter);
    }

}
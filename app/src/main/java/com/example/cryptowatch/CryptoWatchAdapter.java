package com.example.cryptowatch;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class CryptoWatchAdapter extends RecyclerView.Adapter<CryptoWatchAdapter.WordViewHolder>{
    private LayoutInflater mInflator;
    private LinkedList<CryptoItem> mCryptoList;
    private Context context;

    public CryptoWatchAdapter(Context context, LinkedList<CryptoItem> cryptolist){
        mInflator = LayoutInflater.from(context);
        mCryptoList = cryptolist;
        this.context = context;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //WHERE YOU PLACE DATA
        View mItemView = mInflator.inflate(R.layout.crypto_item,parent,false);
        return new WordViewHolder(mItemView,this);
    }

    // To Load image from URL
    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoWatchAdapter.WordViewHolder holder, int position) {
        String mCurrentName = mCryptoList.get(position).name;
        final DecimalFormat df = new DecimalFormat("0.00");
        String mCurrentPrice = "$" + df.format(mCryptoList.get(position).price);
        String mCurrentSymbol = mCryptoList.get(position).symbol;
        holder.mCryptoPrice.setText(mCurrentPrice);
        holder.mCryptoSymbol.setText(mCurrentSymbol);
        Picasso.get().load(mCryptoList.get(position).imageURL).into( holder.mCryptoImageView);
    }

    @Override
    public int getItemCount() {
        return mCryptoList.size();
    }
    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //instantiate any views used in the item layout here
        private TextView mCryptoName;
        private TextView mCryptoPrice;
        private TextView mCryptoSymbol;
        private ImageView mCryptoImageView;
        private CryptoWatchAdapter mAdapter;
        public WordViewHolder(View itemView, CryptoWatchAdapter adapter) {
            super(itemView);
            mCryptoPrice = itemView.findViewById(R.id.cryptoPrice);
            mCryptoSymbol = itemView.findViewById(R.id.cryptoSymbol);
            mCryptoImageView = itemView.findViewById(R.id.cryptoImage);

            this.mAdapter = adapter;


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }


}




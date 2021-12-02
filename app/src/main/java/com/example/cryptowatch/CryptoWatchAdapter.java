package com.example.cryptowatch;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

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

    @Override
    public void onBindViewHolder(@NonNull CryptoWatchAdapter.WordViewHolder holder, int position) {
        String mCurrentName = mCryptoList.get(position).name;
        String mCurrentPrice = Float.toString(mCryptoList.get(position).price);
        String mCurrentSymbol = mCryptoList.get(position).symbol;
        holder.mCryptoName.setText(mCurrentName);
        holder.mCryptoPrice.setText(mCurrentPrice);
        holder.mCryptoSymbol.setText(mCurrentSymbol);
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
        private CryptoWatchAdapter mAdapter;
        public WordViewHolder(View itemView, CryptoWatchAdapter adapter) {
            super(itemView);
            mCryptoName = itemView.findViewById(R.id.cryptoName);
            mCryptoPrice = itemView.findViewById(R.id.cryptoPrice);
            mCryptoSymbol = itemView.findViewById(R.id.cryptoSymbol);

            this.mAdapter = adapter;


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }


}




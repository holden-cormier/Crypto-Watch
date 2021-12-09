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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.LinkedList;

// Handles the recylcer view on MainActivity
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
        View mItemView = mInflator.inflate(R.layout.crypto_item,parent,false);
        return new WordViewHolder(mItemView,this);
    }


    /**
     * Handles updating each UI item with data from the linkedlist containing crypto items
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull CryptoWatchAdapter.WordViewHolder holder, int position) {
        String mCurrentName = mCryptoList.get(position).name;
        final DecimalFormat df = new DecimalFormat("0.00");
        String mCurrentPrice = "$" + df.format(mCryptoList.get(position).price);
        String mCurrentSymbol = mCryptoList.get(position).symbol;
        holder.mCryptoPrice.setText(mCurrentPrice);
        holder.mCryptoSymbol.setText(mCurrentSymbol);
        Picasso.get().load(mCryptoList.get(position).imageURL).into( holder.mCryptoImageView);

        CryptoItem cryptoItem = mCryptoList.get(position);
        Intent i = new Intent(context, CryptoWatchDetailActivity.class);
        i.putExtra("CryptoItem", cryptoItem);

        holder.mCryptoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCryptoList.size();
    }


    // Reference to the crypto item
    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mCryptoName;
        private TextView mCryptoPrice;
        private TextView mCryptoSymbol;
        private ImageView mCryptoImageView;
        private CardView mCryptoCardView;
        private CryptoWatchAdapter mAdapter;


        public WordViewHolder(View itemView, CryptoWatchAdapter adapter) {
            super(itemView);
            mCryptoPrice = itemView.findViewById(R.id.cryptoPrice);
            mCryptoSymbol = itemView.findViewById(R.id.cryptoSymbol);
            mCryptoImageView = itemView.findViewById(R.id.cryptoImage);
            mCryptoCardView = itemView.findViewById(R.id.cryptoItemCardView);

            this.mAdapter = adapter;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }


}




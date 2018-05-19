package com.arumaya.mpuzzle.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arumaya.mpuzzle.BR;
import com.arumaya.mpuzzle.R;
import com.arumaya.mpuzzle.databinding.CardItemBinding;
import com.arumaya.mpuzzle.model.entity.Card;
import com.arumaya.mpuzzle.view.ui.CardClickCallback;

import java.util.List;
import java.util.Objects;

/**
 * Created by Owner on 7/2/2018.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private static final String TAG = "CardAdapter";

    List<? extends Card> mCardList;

    @Nullable
    private final CardClickCallback mCardClickCallback;

    public CardAdapter(@Nullable CardClickCallback clickCallback) {
        Log.d(TAG, "DEBUG: Construct");
        mCardClickCallback = clickCallback;
    }

    public void setCards(final List<? extends Card> cardList) {
        Log.d(TAG, "DEBUG: setProductList");
        if (mCardList == null) {
            mCardList = cardList;
            notifyItemRangeInserted(0, cardList.size());
        } else {
            // TODO: this is interesting to have calc diff library but for my case is not needed - later before publish, remove it.
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mCardList.size();
                }

                @Override
                public int getNewListSize() {
                    return cardList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mCardList.get(oldItemPosition).getId() ==
                            cardList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Card newProduct = cardList.get(newItemPosition);
                    Card oldProduct = mCardList.get(oldItemPosition);
                    return newProduct.getId() == oldProduct.getId()
                            && Objects.equals(newProduct.getId(), oldProduct.getId())
                            && Objects.equals(newProduct.getValue(), oldProduct.getValue())
                            && newProduct.getOwner() == oldProduct.getOwner();
                }
            });
            mCardList = cardList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "DEBUG: onCreateViewHolder");

        CardItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.card_item,
                        parent, false);
        binding.setCallback(mCardClickCallback);
        return new CardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Log.d(TAG, "DEBUG: onBindViewHolder, Pos: " + position);

        holder.bind(mCardList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCardList == null ? 0 : mCardList.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {

        final CardItemBinding binding;

        public CardViewHolder(CardItemBinding binding) {
            super(binding.getRoot());
            Log.d(TAG, "DEBUG: CardViewHolder - Construct");
            this.binding = binding;
        }

        public void bind(@NonNull Object obj) {
            binding.setVariable(BR.obj, obj);
            binding.executePendingBindings();
        }
    }

}

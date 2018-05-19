package com.arumaya.mpuzzle.model.dao;

import android.arch.lifecycle.LiveData;

import com.arumaya.mpuzzle.model.entity.Card;

import java.util.List;

/**
 * Created by andre on 4/2/2018.
 */

public class CardDao_impl implements CardDao{

    public CardDao_impl() {

    }

    @Override
    public LiveData<List<Card>> loadAllCards() {
        return null;
    }

    @Override
    public void insertAll(List<Card> cards) {

    }

    @Override
    public LiveData<Card> loadCard(int cardId) {
        return null;
    }

}

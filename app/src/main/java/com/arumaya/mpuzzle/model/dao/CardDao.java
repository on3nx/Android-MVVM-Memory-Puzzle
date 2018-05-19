package com.arumaya.mpuzzle.model.dao;

import android.arch.lifecycle.LiveData;

import com.arumaya.mpuzzle.model.entity.Card;

import java.util.List;

/**
 * Created by andre on 4/2/2018.
 */

public interface CardDao {
    LiveData<List<Card>> loadAllCards();
    void insertAll(List<Card> cards);
    LiveData<Card> loadCard(int cardId);

    //CardEntity loadCardSync(int cardId);
}

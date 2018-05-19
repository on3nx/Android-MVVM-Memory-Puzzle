package com.arumaya.mpuzzle.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.arumaya.mpuzzle.model.dao.CardDao;
import com.arumaya.mpuzzle.model.dao.PlayerDao;
import com.arumaya.mpuzzle.model.entity.Card;
import com.arumaya.mpuzzle.model.entity.Player;

import java.util.List;

/**
 * Created by Owner on 6/2/2018.
 */

public abstract class GameAbstract {

    private static final String TAG = "GameAbstract";
    private static GameAbstract sInstance;

    //public abstract CardDao cardDao();
    //public abstract PlayerDao playerDao();

    public abstract LiveData<List<Player>> loadAllPlayers();
    public abstract LiveData<List<Card>> loadAllCards();
    public abstract LiveData<Card> loadCard(int cardId);
    public abstract LiveData<Player> loadPlayer(int playerId);

    public abstract void closeCard(Card card);
    public abstract void openCard(Card card);

    private final MutableLiveData<Boolean> mIsGameCreated = new MutableLiveData<>();

    public static GameAbstract getInstance(final Integer noCard, final Integer noPlayer) {
        Log.d(TAG, "DEBUG: Get Instance");
        if (sInstance == null) {
            synchronized (GameAbstract.class) {
                if (sInstance == null) {
                    Log.d(TAG, "DEBUG: New Instance Created");
                    sInstance = new Game(noCard, noPlayer);//Game.getInstance(noCard, noPlayer);
                    sInstance.setGameCreated(true);
                    Log.d(TAG, "DEBUG: " + sInstance.getGameCreated().getValue());
                }
            }
        }
        return sInstance;
    }

    public LiveData<Boolean> getGameCreated() { return mIsGameCreated; }
    private void setGameCreated(Boolean isCreated){ mIsGameCreated.setValue(isCreated); }

}

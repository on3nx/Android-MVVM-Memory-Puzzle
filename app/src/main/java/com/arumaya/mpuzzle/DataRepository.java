package com.arumaya.mpuzzle;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.util.Log;

import com.arumaya.mpuzzle.model.GameAbstract;
import com.arumaya.mpuzzle.model.entity.Card;
import com.arumaya.mpuzzle.model.entity.Player;

import java.util.List;

/**
 * Created by Owner on 6/2/2018.
 */

public class DataRepository {

    private static final String TAG = "DataRepository";
    private static DataRepository sInstance;

    private final GameAbstract mGame;
    private MediatorLiveData<List<Player>> mObservablePlayers;
    private MediatorLiveData<List<Card>> mObservableCards;

    private DataRepository(final GameAbstract game) {
        mGame = game;

        mObservablePlayers = new MediatorLiveData<>();
        mObservablePlayers.addSource( mGame.loadAllPlayers(),
                playerEntities -> {
                    if (mGame.getGameCreated().getValue() != null) {
                        mObservablePlayers.postValue(playerEntities);
                    }
                });

        mObservableCards = new MediatorLiveData<>();
        mObservableCards.addSource( mGame.loadAllCards(),
                cardEntities -> {
                    if (mGame.getGameCreated().getValue() != null) {
                        mObservableCards.postValue(cardEntities);
                    }
                });
    }

    public static DataRepository getInstance(final GameAbstract database) {
        Log.d(TAG, "DEBUG: GetInstance");
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    Log.d(TAG, "DEBUG: New Instance Created");
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<Player>> getPlayers() { return mObservablePlayers; }
    public LiveData<List<Card>> getCards() { return mObservableCards; }
    public LiveData<Player> getTurn() { return mGame.loadPlayer(0); }

    public LiveData<Card> loadCard(final int cardId) { return mGame.loadCard(cardId); }
    public LiveData<Player> loadPlayer(final int playerId) { return mGame.loadPlayer(playerId); }

}

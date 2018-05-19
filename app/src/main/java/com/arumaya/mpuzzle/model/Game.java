package com.arumaya.mpuzzle.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Handler;

import com.arumaya.mpuzzle.model.entity.Card;
import com.arumaya.mpuzzle.model.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Owner on 6/2/2018.
 */

public class Game extends GameAbstract {

    private List<Card> mCards;
    private List<Player> mPlayers;

    private int currentTurn = 0;
    private int getNextTurn(){ return (currentTurn+1) < mPlayers.size() ? currentTurn+1 : 0; }
    private void setNextTurn(){ currentTurn = getNextTurn(); }

    private Card temp = null;

    private boolean mEnabled = true;
    private boolean getEnabled(){ return mEnabled; }
    private void seEnabled(boolean enabled){ mEnabled = enabled; }

    public interface DelayCallback{ void afterDelay(); }
    public static void delay(int secs, final DelayCallback delayCallback){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                delayCallback.afterDelay();
            }
        }, secs * 1000); // afterDelay will be executed after (secs*1000) milliseconds.
    }

    Game(Integer noCard, Integer noPlayer) {
        // Set Card
        mCards = new ArrayList<>();
        List<CardType> cardTypes = CardType.getShuffledCard().subList(0, (noCard/2));
        for (CardType item : cardTypes){
            mCards.add(new Card(mCards.size(), item.getValue(), item.getStatus(), null));
            mCards.add(new Card(mCards.size(), item.getValue(), item.getStatus(), null));
        }
        Collections.shuffle(mCards);

        // Set Player
        mPlayers = new ArrayList<>();
        for (int i = 0; i < noPlayer; i++) {
            mPlayers.add(new Player(i, "Player " + (i + 1), 0));
        }

    }

    @Override
    public LiveData<List<Player>> loadAllPlayers(){
        MutableLiveData<List<Player>> players = new MutableLiveData<>();
        players.setValue(mPlayers);
        return players;
    }

    @Override
    public LiveData<List<Card>> loadAllCards(){
        MutableLiveData<List<Card>> cards = new MutableLiveData<>();
        cards.setValue(mCards);
        return cards;
    }

    public void openCard(Card card) {
        if(getEnabled()) {
            card.setStatus(true);
            if (temp == null) {
                temp = card;
            } else {
                if (temp.getValue() == card.getValue()) {
                    temp.setOwner(mPlayers.get(currentTurn));
                    card.setOwner(mPlayers.get(currentTurn));
                    mPlayers.get(currentTurn).setScore(100 + temp.getPoint() + card.getPoint());

                    temp = null;
                    setNextTurn();
                } else {
                    seEnabled(false);
                    Game.delay(1, new DelayCallback() {
                        @Override
                        public void afterDelay() {
                            if (temp.getPoint() > 0) temp.setPoint(temp.getPoint() - 1);
                            temp.setStatus(false);

                            if (card.getPoint() > 0) card.setPoint(card.getPoint() - 1);
                            card.setStatus(false);

                            temp = null;
                            setNextTurn();

                            seEnabled(true);
                        }
                    });
                }
            }
        }
    }
    public void closeCard(Card card) {
        if(getEnabled()) {
            mPlayers.get(currentTurn).setScore(
                    mPlayers.get(currentTurn).getScore() + card.getPoint()
            );

            if (card.getPoint() > 0) card.setPoint(card.getPoint() - 1);
            card.setStatus(false);
            temp = null;
            setNextTurn();
        }
    }

    @Override
    public LiveData<Card> loadCard(int cardId) {
        return null;
    }

    @Override
    public LiveData<Player> loadPlayer(int playerId) {
        return null;
    }

}

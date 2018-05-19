package com.arumaya.mpuzzle.model.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.arumaya.mpuzzle.BR;
import com.arumaya.mpuzzle.model.PlayerInterface;

/**
 * Created by Owner on 6/2/2018.
 */

public class Player extends BaseObservable implements PlayerInterface {
    private int mId;
    @Override
    public int getId() { return this.mId; }
    //@Override
    private void setId(int id){ this.mId = id; }

    private String mName;
    @Override
    public String getName() { return this.mName; }
    //@Override
    private void setName(String name){ this.mName = name; }

    private int mScore;
    @Override @Bindable
    public int getScore() { return this.mScore; }
    @Override
    public void setScore(int score){
        this.mScore = score;
        notifyPropertyChanged(BR.score);
    }

    public Player() { }

    public Player(int id, String name, int score) {
        setId(id);
        setName(name);
        setScore(score);
    }

    public Player(Player player) {
        setId(player.getId());
        setName(player.getName());
        setScore(player.getScore());
    }

}

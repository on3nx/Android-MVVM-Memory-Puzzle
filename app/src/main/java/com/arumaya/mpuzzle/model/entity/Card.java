package com.arumaya.mpuzzle.model.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.Nullable;

import com.arumaya.mpuzzle.BR;
import com.arumaya.mpuzzle.R;
import com.arumaya.mpuzzle.model.CardInterface;

/**
 * Created by Owner on 6/2/2018.
 */

public class Card extends BaseObservable implements CardInterface {

    private int mId;
    @Override
    public int getId() { return this.mId; }
    //@Override
    private void setId(int id){ this.mId = id; }

    private static final int mBACK = R.drawable.ic_help_outline_black_48px;
    @Override @Bindable
    public int getBackValue() { return mBACK; }

    private int mValue;
    @Override @Bindable
    public int getValue() { return this.mValue; }
    //@Override
    private void setValue(int value){ this.mValue = value; }

    private int mPoint;
    @Override @Bindable
    public int getPoint() { return this.mPoint; }
    @Override
    public void setPoint(int point){
        this.mPoint = point;
        notifyPropertyChanged(BR.point);
    }

    private Boolean mStatus;
    @Override @Bindable
    public Boolean getStatus() { return mStatus; }
    @Override
    public void setStatus(Boolean status){
        this.mStatus = status;
        notifyPropertyChanged(BR.status);
    }

    private Player mOwner;
    @Override @Bindable
    public Player getOwner() { return mOwner; }
    @Override
    public void setOwner(Player owner){
        this.mOwner = owner;
        notifyPropertyChanged(BR.owner);
    }

    public Card() { }

    public Card(int id, int value, Boolean status, @Nullable Player owner) {
        setId(id);
        setValue(value);
        setPoint(100);
        setStatus(status);
        setOwner(owner);
    }

    public Card(Card card) {
        setId(card.getId());
        setValue(card.getValue());
        setPoint(100);
        setStatus(card.getStatus());
        setOwner(card.getOwner());
    }
}

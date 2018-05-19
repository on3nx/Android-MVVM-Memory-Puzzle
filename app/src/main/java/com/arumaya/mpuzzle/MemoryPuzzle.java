package com.arumaya.mpuzzle;

import android.app.Application;

import com.arumaya.mpuzzle.model.GameAbstract;

/**
 * Created by Owner on 6/2/2018.
 */

public class MemoryPuzzle extends Application {

    private Integer mCardQty;
    public Integer getCardQty(){ return mCardQty; }
    public void setCardQty(Integer qty){ mCardQty = qty; }

    private Integer mNoPlayer;
    public Integer getNoPlayer(){ return mNoPlayer; }
    public void setNoPlayer(Integer player){ mNoPlayer = player; }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public DataRepository getRepository() {return DataRepository.getInstance(getDatabase()); }
    public GameAbstract getDatabase() { return GameAbstract.getInstance(getCardQty(), getNoPlayer()); }

}

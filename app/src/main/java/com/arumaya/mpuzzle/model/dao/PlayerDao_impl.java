package com.arumaya.mpuzzle.model.dao;

import android.arch.lifecycle.LiveData;

import com.arumaya.mpuzzle.model.entity.Player;

import java.util.List;

/**
 * Created by andre on 4/2/2018.
 */

public class PlayerDao_impl implements PlayerDao {

    public PlayerDao_impl() { }

    @Override
    public LiveData<List<Player>> loadAllPlayers() {
        return null;
    }

    @Override
    public void insertAll(List<Player> players) {

    }

    @Override
    public LiveData<Player> loadPlayer(int playerId) {
        return null;
    }
}

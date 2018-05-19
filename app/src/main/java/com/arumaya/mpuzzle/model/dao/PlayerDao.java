package com.arumaya.mpuzzle.model.dao;

import android.arch.lifecycle.LiveData;

import com.arumaya.mpuzzle.model.entity.Player;

import java.util.List;

/**
 * Created by andre on 4/2/2018.
 */

public interface PlayerDao {
    LiveData<List<Player>> loadAllPlayers();
    void insertAll(List<Player> players);
    LiveData<Player> loadPlayer(int playerId);
}

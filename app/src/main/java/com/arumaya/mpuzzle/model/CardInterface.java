package com.arumaya.mpuzzle.model;

import com.arumaya.mpuzzle.model.entity.Player;

/**
 * Created by Owner on 6/2/2018.
 */

public interface CardInterface {
    int getId();
    //void setId(int id);
    int getBackValue();
    int getValue();
    //void setValue(int value);
    int getPoint();
    void setPoint(int point);
    Boolean getStatus();
    void setStatus(Boolean status);
    Player getOwner();
    void setOwner(Player owner);
}

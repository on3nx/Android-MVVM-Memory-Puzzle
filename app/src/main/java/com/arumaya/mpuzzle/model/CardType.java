package com.arumaya.mpuzzle.model;

import com.arumaya.mpuzzle.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum CardType {
    ACCESSIBILITY(R.drawable.ic_accessibility_white_48px, false),
    AIRPLANEMODE(R.drawable.ic_airplanemode_active_white_48px, false),
    ALARM(R.drawable.ic_alarm_on_white_48px, false),
    BACKUP(R.drawable.ic_backup_white_48px, false),
    BUBBLE(R.drawable.ic_bubble_chart_white_48px, false),
    BUG(R.drawable.ic_bug_report_white_48px, false),
    CALL(R.drawable.ic_call_white_48px, false),
    CAMERA(R.drawable.ic_camera_alt_white_48px, false),
    CHILD(R.drawable.ic_child_care_white_48px, false),
    COMPUTER(R.drawable.ic_computer_white_48px, false),
    DIRECTIONS(R.drawable.ic_directions_bike_white_48px, false),
    DRAFTS(R.drawable.ic_drafts_white_48px, false),
    FACE(R.drawable.ic_face_white_48px, false),
    FAVORITE(R.drawable.ic_favorite_white_48px, false),
    FILTER(R.drawable.ic_filter_7_white_48px, false),
    FLIGHT(R.drawable.ic_flight_takeoff_white_48px, false),
    SHOPPING(R.drawable.ic_shopping_cart_white_48px, false),
    SMARTPHONE(R.drawable.ic_smartphone_white_48px, false),
    STAR(R.drawable.ic_star_white_48px, false),
    TAG(R.drawable.ic_tag_faces_white_48px, false),
    TOYS(R.drawable.ic_toys_white_48px, false),
    TRAFFIC(R.drawable.ic_traffic_white_48px, false),
    WC(R.drawable.ic_wc_white_48px, false),
    WHATSHOT(R.drawable.ic_whatshot_white_48px, false);

    private final int mValue;
    public int getValue() { return this.mValue; }

    private Boolean mStatus;
    public Boolean getStatus(){ return this.mStatus; }
    public void setStatus(Boolean val){ this.mStatus = val; }

    CardType(int value, Boolean status) {
        this.mValue = value;
        this.setStatus(status);
    }

    private static final List<CardType> VALUES = Arrays.asList(values());
    public static List<CardType> getShuffledCard() {
        Collections.shuffle(VALUES);
        return VALUES;
    }
}

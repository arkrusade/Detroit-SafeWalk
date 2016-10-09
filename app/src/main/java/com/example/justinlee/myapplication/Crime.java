package com.example.justinlee.myapplication;

/**
 * Created by justinlee on 10/8/16.
 */

public class Crime {
    crimeCategory category;
    int danger;
}
enum crimeCategory {


    AGGRAVATED_ASSAULT(3),
    ASSAULT(3),
    BURGLARY(3),
    ROBBERY(3),
    LARCENY(3),
    WEAPONS(3),
    MISCELLANEOUS(3),
    OBSTRUCTING_JUDICIARY(3),
    DAMAGE_TO_PROPERTY(2),
    STOLEN_VEHICLE(1),
    STOLEN_PROPERTY(1),
    ABORTION(0),
    SOLICITATION(0),
    RUNAWAY(0),
    ARSON(2),
    BRIBERY(1),
    CIVIL(0),
    DANGEROUS_DRUGS(1),
    VAGRANCY(0),
    TRAFFIC(0),
    TRAFFIC_OFFENSES(0),
    ;

    private int danger;

    public int getDanger() {
        return danger;
    }

    crimeCategory(int danger) {
        this.danger = danger;
    }
}
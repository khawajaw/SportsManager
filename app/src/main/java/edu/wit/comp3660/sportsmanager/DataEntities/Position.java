package edu.wit.comp3660.sportsmanager.DataEntities;

import java.util.ArrayList;

public enum Position {
    NONE ("Select Position..."),

    SOCCER_GK ("GK"),
    SOCCER_LB ("LB"),
    SOCCER_RB ("RB"),
    SOCCER_LCB ("LCB"),
    SOCCER_RCB ("RCB"),
    SOCCER_CDM ("CDM"),
    SOCCER_LCM ("LCM"),
    SOCCER_RCM ("RCM"),
    SOCCER_LM ("LM"),
    SOCCER_RM ("RM"),
    SOCCER_ST ("ST"),
    BASKETBALL_PG ("PG"),
    BASKETBALL_C ("C"),
    BASKETBALL_SF ("SF"),
    BASKETBALL_PF ("PF"),
    BASKETBALL_SG ("SG"),
    BASEBALL_CATCHER ("CATCHER"),
    BASEBALL_PITCHER ("PITCHER"),
    BASEBALL_B1 ("1B"),
    BASEBALL_B2 ("2B"),
    BASEBALL_SS ("SS"),
    BASEBALL_B3 ("3B"),
    BASEBALL_RF ("RF"),
    BASEBALL_LF ("LF"),
    BASEBALL_CF ("CF"),
    FOOTBALL_DEF_CB1 ("CB1"),
    FOOTBALL_DEF_CB2 ("CB2"),
    FOOTBALL_DEF_RDE ("RDE"),
    FOOTBALL_DEF_LDE ("LDE"),
    FOOTBALL_DEF_DT1 ("DT1"),
    FOOTBALL_DEF_DT2 ("DT2"),
    FOOTBALL_DEF_OL1 ("OL1"),
    FOOTBALL_DEF_ML ("ML"),
    FOOTBALL_DEF_OL2 ("OL2"),
    FOOTBALL_DEF_FS ("FS"),
    FOOTBALL_DEF_SS ("SS"),
    FOOTBALL_OFF_QB ("QB"),
    FOOTBALL_OFF_RB1 ("RB1"),
    FOOTBALL_OFF_RB2 ("RB2"),
    FOOTBALL_OFF_WR1 ("WR1"),
    FOOTBALL_OFF_WR2 ("WR2"),
    FOOTBALL_OFF_TE ("TE"),
    FOOTBALL_OFF_LT ("LT"),
    FOOTBALL_OFF_RT ("RT"),
    FOOTBALL_OFF_LG ("LG"),
    FOOTBALL_OFF_RG ("RG"),
    FOOTBALL_OFF_C ("C"),
    FOOTBALL_ST_K ("K"),
    FOOTBALL_ST_H ("H"),
    FOOTBALL_ST_R ("R"),
    FOOTBALL_ST_P ("P"),
    FOOTBALL_ST_G ("G");

    private final String text;

    Position() {text = "Firebase failure";}

    Position(String text) {
        this.text = text;
    }

    public static ArrayList<Position> getSoccerPos() {
        ArrayList<Position> soccerPos = new ArrayList<>();
        for(int i = 0; i < 11; i++) {
            switch(i) {
                case 0:
                    soccerPos.add(SOCCER_GK);
                    break;
                case 1:
                    soccerPos.add(SOCCER_LB);
                    break;
                case 2:
                    soccerPos.add(SOCCER_RB);
                    break;
                case 3:
                    soccerPos.add(SOCCER_LCB);
                    break;
                case 4:
                    soccerPos.add(SOCCER_RCB);
                    break;
                case 5:
                    soccerPos.add(SOCCER_CDM);
                    break;
                case 6:
                    soccerPos.add(SOCCER_LCM);
                    break;
                case 7:
                    soccerPos.add(SOCCER_RCM);
                    break;
                case 8:
                    soccerPos.add(SOCCER_LM);
                    break;
                case 9:
                    soccerPos.add(SOCCER_RM);
                    break;
                case 10:
                    soccerPos.add(SOCCER_ST);
                    break;
            }
        }

        return soccerPos;
    }

    public static ArrayList<Position> getBasketballPos() {
        ArrayList<Position> basketballPos = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            switch(i) {
                case 0:
                    basketballPos.add(BASKETBALL_PG);
                    break;
                case 1:
                    basketballPos.add(BASKETBALL_C);
                    break;
                case 2:
                    basketballPos.add(BASKETBALL_SF);
                    break;
                case 3:
                    basketballPos.add(BASKETBALL_PF);
                    break;
                case 4:
                    basketballPos.add(BASKETBALL_SG);
                    break;
            }
        }

        return basketballPos;
    }

    public static ArrayList<Position> getBaseballPos() {
        ArrayList<Position> baseballPos = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            switch(i) {
                case 0:
                    baseballPos.add(BASEBALL_CATCHER);
                    break;
                case 1:
                    baseballPos.add(BASEBALL_PITCHER);
                    break;
                case 2:
                    baseballPos.add(BASEBALL_B1);
                    break;
                case 3:
                    baseballPos.add(BASEBALL_B2);
                    break;
                case 4:
                    baseballPos.add(BASEBALL_SS);
                    break;
                case 5:
                    baseballPos.add(BASEBALL_B3);
                    break;
                case 6:
                    baseballPos.add(BASEBALL_RF);
                    break;
                case 7:
                    baseballPos.add(BASEBALL_LF);
                    break;
                case 8:
                    baseballPos.add(BASEBALL_CF);
                    break;
            }
        }

        return baseballPos;
    }

    public static ArrayList<Position> getFootballPos() {
        ArrayList<Position> footballPos = new ArrayList<>();
        for(int i = 0; i < 27; i++) {
            switch(i) {
                case 0:
                    footballPos.add(FOOTBALL_DEF_CB1);
                    break;
                case 1:
                    footballPos.add(FOOTBALL_DEF_CB2);
                    break;
                case 2:
                    footballPos.add(FOOTBALL_DEF_RDE);
                    break;
                case 3:
                    footballPos.add(FOOTBALL_DEF_LDE);
                    break;
                case 4:
                    footballPos.add(FOOTBALL_DEF_DT1);
                    break;
                case 5:
                    footballPos.add(FOOTBALL_DEF_DT2);
                    break;
                case 6:
                    footballPos.add(FOOTBALL_DEF_OL1);
                    break;
                case 7:
                    footballPos.add(FOOTBALL_DEF_ML);
                    break;
                case 8:
                    footballPos.add(FOOTBALL_DEF_OL2);
                    break;
                case 9:
                    footballPos.add(FOOTBALL_DEF_FS);
                    break;
                case 10:
                    footballPos.add(FOOTBALL_DEF_SS);
                    break;
                case 11:
                    footballPos.add(FOOTBALL_OFF_QB);
                    break;
                case 12:
                    footballPos.add(FOOTBALL_OFF_RB1);
                    break;
                case 13:
                    footballPos.add(FOOTBALL_OFF_RB2);
                    break;
                case 14:
                    footballPos.add(FOOTBALL_OFF_WR1);
                    break;
                case 15:
                    footballPos.add(FOOTBALL_OFF_WR2);
                    break;
                case 16:
                    footballPos.add(FOOTBALL_OFF_TE);
                    break;
                case 17:
                    footballPos.add(FOOTBALL_OFF_LT);
                    break;
                case 18:
                    footballPos.add(FOOTBALL_OFF_RT);
                    break;
                case 19:
                    footballPos.add(FOOTBALL_OFF_LG);
                    break;
                case 20:
                    footballPos.add(FOOTBALL_OFF_RG);
                    break;
                case 21:
                    footballPos.add(FOOTBALL_OFF_C);
                    break;
                case 22:
                    footballPos.add(FOOTBALL_ST_K);
                    break;
                case 23:
                    footballPos.add(FOOTBALL_ST_H);
                    break;
                case 24:
                    footballPos.add(FOOTBALL_ST_R);
                    break;
                case 25:
                    footballPos.add(FOOTBALL_ST_P);
                    break;
                case 26:
                    footballPos.add(FOOTBALL_ST_G);
                    break;
            }
        }

        return footballPos;
    }

    @Override
    public String toString() {
        return text;
    }
}

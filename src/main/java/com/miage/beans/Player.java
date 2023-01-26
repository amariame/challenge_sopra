package com.miage.beans;

import lombok.Data;

@Data
public class Player {
    private String playerName;
    private String inventory;
    private int leaderboardPosition;
    private String lastMove;
    private boolean stunned;

    private Position position;

}

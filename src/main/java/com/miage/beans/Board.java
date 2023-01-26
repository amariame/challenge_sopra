package com.miage.beans;

import lombok.Data;

import java.util.List;

@Data
public class Board {
    private int nbrTurnsLeft;
    private Player selfPlayer;
    private List<Player> players;
    private List<Item> items;
    private List<Bonus> bonuses;

}

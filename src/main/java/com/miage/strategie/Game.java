package com.miage.strategie;

import com.miage.KartClientRestApi;
import com.miage.MoteurDeJeu;
import com.miage.beans.Item;
import com.miage.beans.Position;

import java.util.List;

public class Game extends Thread{

    private final int numBot;
    private MoteurDeJeu mdj;
    private final String partieId;
    
    public Game(int numBot, MoteurDeJeu mdj){
        this.numBot = numBot;
        this.mdj = mdj;
        this.partieId = mdj.startPartie(numBot);
    }


    public void run(String partieId){

        /*System.out.println("Partie en cours.....");
        List<Item> itemsPosition = mdj.item_board(partieId);
        String status = null;

        do {

            status = mdj.getStatus(partieId);

            if (status.equals("CANPLAY")) {

                Position myPosition = mdj.getBoard(partieId).getSelfPlayer().getPosition();

                status = mdj.play(partieId, mdj.mouve(myPosition,itemsPosition));
            }
        } while (!status .equals("RANKING") || !status.equals("RANKED"));

        System.out.println("Partie terminee "+partieId);*/
    }
}

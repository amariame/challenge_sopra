package com.miage;


import com.google.gson.Gson;
import com.miage.beans.Board;
import com.miage.beans.Item;
import com.miage.beans.Position;

import java.util.List;
import java.util.stream.Collectors;

public class MoteurDeJeu {

    private final String idEquipe;

    private final KartClientApi kartClientApi;


    public  MoteurDeJeu(KartClientApi kartClientApi){

        this.kartClientApi = kartClientApi;
        this.idEquipe = this.kartClientApi.getEquipeId();
    }

    public String ping(){
        return  this.kartClientApi.ping();
    }


    public String getEquipeId(){
        return this.kartClientApi.getEquipeId();
    }

    public String newPractice(int idBot){
        return this.kartClientApi.practice(idBot,this.idEquipe);
    }

    public String newVersus(){
        return  this.kartClientApi.versus(this.idEquipe);
    }

    public String getStatus(String idPartie){
        return  this.kartClientApi.getStatus(idPartie,this.idEquipe);
    }

    public Board getBoard(String idPartie){

        return jsonSereliaze(this.kartClientApi.getBoard(idPartie,this.idEquipe));
    }

    public String play(String idPartie, String action){
        return this.kartClientApi.play(idPartie,this.idEquipe,action);
    }

    private Board jsonSereliaze(String json){
        Gson gson = new Gson();
        Board plateau = gson.fromJson(json, Board.class);

        return plateau;
    }

    public List<Item> item_board(String idPartie){
        List<Item> items = this.getBoard(idPartie).getItems();

        return  items;
    }
    


    public String mouve(Position p, Board board){
        List<Item> items = board.getItems();
        String move = "FORWARD";

        List<Position> taches =items.stream().map(Item::getPosition).collect(Collectors.toList());
//        List<Position> taches =items.stream()
//                .filter(item -> item.getType().equals("O"))
//                .map(Item::getPosition)
//                .collect(Collectors.toList());

        //move(position)
        Position p1 =new Position();
        Position p2 =new Position();
        int lane = p.getLane();
        int row = p.getRow();

        p1.setLane(lane);
        p1.setRow(row+1);
        p2.setLane(lane);
        p2.setRow(row+2);

        if(board.getSelfPlayer().getInventory()!=null){

            System.out.println("Bonus !!!!!!");
            if(!board.getSelfPlayer().getInventory().equals("R"))
                move = "USE_BONUS";
        }

        if(taches.contains(p2) || taches.contains(p1)){
            if(p.getLane() == 0){
                p1.setLane(p1.getLane()+1);
                move = "RIGHT";
            } else if (p.getLane() ==4) {
                p1.setLane(p1.getLane()-1);
                move = "LEFT";
            }
            else {
                p1.setLane(p1.getLane()-1);
                if(taches.contains(p1)){
                    move= "RIGHT";
                }
                else {
                    move= "LEFT";
                }
            }
        }

        System.out.println("action :"+move);
        return  move;
    }

    /**
     * Lance une nouvelle partie
     * versus si bot vaut -1
     * sinon practice
     * @param bot numero de bot
     * @return String
     */
    public String startPartie( int bot) throws InterruptedException {
        System.out.println("Start new partie.....");
        String partieId="NA";
        while (partieId.equals("NA")){
            //System.out.println("attente "+partieId);
            Thread.sleep(100);
            partieId = bot == - 1
                    ? this.newVersus()
                    : this.newPractice(bot);
        }
        System.out.println("Partie "+partieId);
        /*if(bot == -1){
            System.out.println("NOUVELLE PARTIE VERSUS");
            System.out.println("Creation de la partie ....");
            System.out.println("wait....");
            while (partieId.equals("NA")) {
                System.out.println("waiting....");
                partieId = this.newVersus();
            }
            System.out.println("done lests go -:)");
            System.out.println("Equipe "+this.idEquipe);
            System.out.println("Versus "+partieId);
        }
        else {
            System.out.println("NOUVELLE PARTIE BOT");
            System.out.println("Creation de la partie ....");
            while (partieId.equals("NA")) {
                partieId = this.newPractice(bot);
            }
            System.out.println("wait....");
            System.out.println("Partie crée :-)");
        }*/

        return partieId;
    }
}

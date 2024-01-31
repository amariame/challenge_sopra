package com.miage;

import com.miage.beans.Item;
import com.miage.beans.Position;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.List;

public class BattleMain {
    public static void main(String[] args) throws ParseException, IOException, InterruptedException {

        MoteurDeJeu mdj = new MoteurDeJeu(new KartClientRestApi());

        Options options = new Options();
        options.addOption("e",true,"Lance nouvelle partie avec un bot");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd =  parser.parse(options,args);

        String status = null;

        int numBot = -1;
        String partieId = "NA";

        if(cmd.hasOption('e')) {
            numBot = Integer.parseInt(cmd.getOptionValue('e'));
        }
        partieId = mdj.startPartie(numBot);
        if(partieId.equals("NA")){
            System.out.println("Aucune partie n'est ouverte");
        }
        else {
            System.out.println("Partie en cours.....");
            System.out.println(mdj.getBoard(partieId).getItems());
            //mdj.getBoard(partieId).getItems().stream().map((item -> {}))
            int[] lines = {0,0,0,0};
            do {
                status = mdj.getStatus(partieId);
                List<Item> items = mdj.item_board(partieId);


                if (status.equals("CANPLAY")) {

                    Position myPosition = mdj.getBoard(partieId).getSelfPlayer().getPosition();

                    status = mdj.play(partieId, mdj.mouve(myPosition, mdj.getBoard(partieId)));
                    System.out.println("Status : " + status);

                }
                Thread.sleep(1000);
            } while (!status.equals("RANKING") && !status.equals("RANKED"));

            System.out.println("Partie terminee " + partieId);
        }
    }

}

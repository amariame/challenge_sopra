package com.miage;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MoteurDeJeuTest {



    KartClientStubApiTest kApiClt = new KartClientStubApiTest();
    KartClientRestApi kapi = new KartClientRestApi();
    MoteurDeJeu mdj = new MoteurDeJeu(kApiClt);
    String equipeId = mdj.getEquipeId();
    String partieId = mdj.newPractice(1);

    public MoteurDeJeuTest() throws IOException {
    }


    @Test
    void testCallPingService(){
        Assertions.assertEquals("pong",mdj.callPingService());
    }
    /*
    @Test
    public void  play(){
        Assertions.assertEquals("FORWARD",mdj.getBoard(partieId,equipeId).getSelfPlayer().getLastMove());
    }*/




}

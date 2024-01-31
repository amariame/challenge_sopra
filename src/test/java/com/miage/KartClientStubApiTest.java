package com.miage;

public class KartClientStubApiTest implements KartClientApi {
    public String ping(){
        return "pong";
    }

    @Override
    public String getEquipeId() {
        return "8d6d5488-8c7b-4ba0-bb2f-f37f91149f22";
    }

    @Override
    public String praticeId(int idBot) {
        return null;
    }

    @Override
    public String versus(String idEquipe) {
        return null;
    }

    @Override
    public String practice(int idBot, String idEquipe) {
        return null;
    }

    @Override
    public String getStatus(String idPartie, String idEquipe) {
        return null;
    }

    @Override
    public String getBoard(String idPartie, String idEquipe) {
        return null;
    }

    @Override
    public String play(String idPartie, String idEquipe, String action) {
        return "FORWARD";
    }

    @Override
    public String adversaires(String idPartie, String idEquipe) {
        return null;
    }
}

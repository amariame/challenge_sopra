package com.miage;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class KartClientRestApi implements KartClientApi {

    private final Properties prop;
    private  final HttpClient httpClient = HttpClient.newHttpClient();


    public KartClientRestApi() throws IOException {
        this.prop = new Properties();
        this.prop.load(BattleMain.class.getResourceAsStream("/configuration.properties"));
    }
    @Override
    public String ping() {
        return serviceCall("ping");
    }

    @Override
    public String equipeId() {
        String uri = "player/getIdEquipe/"+prop.getProperty("team.name")+"/"+prop.getProperty("team.password");
        return serviceCall(uri);
    }

    @Override
    public String praticeId(int idBot) {
        String uri = "pratice/new/"+idBot+"/"+this.equipeId();
        return serviceCall(uri);
    }

    @Override
    public String versus(String idEquipe) {
        String uri = "versus/next/"+idEquipe;
        return serviceCall(uri);
    }

    @Override
    public String practice(int idBot, String idEquipe) {
        String uri = "practice/new/"+idBot+"/"+idEquipe;
        return serviceCall(uri);
    }

    @Override
    public String status(String idPartie, String idEquipe) {
        String uri = "game/status/"+idPartie+"/"+idEquipe;
        return serviceCall(uri);
    }

    @Override
    public String board(String idPartie, String idEquipe) {
        //format=(JSON|String|XML)
        String uri = "game/board/"+idPartie+"/"+idEquipe+"?format=JSON";
        String b= serviceCall(uri);

        return serviceCall(uri);
    }

    @Override
    public String play(String idPartie, String idEquipe, String action) {
        //move : L'action de jeu parmi FORWARD, LEFT, RIGHT, BRAKE
        String uri = "game/play/"+idPartie+"/"+idEquipe+"/"+action;
        return serviceCall(uri);
    }

    @Override
    public String adversaires(String idPartie, String idEquipe) {
        String uri = "game/opponents/"+idPartie+"/"+idEquipe;
        return serviceCall(uri);
        //return new String[0];
    }


    private String serviceCall(String urlCall){

        String uri = prop.getProperty("rest.base.url")+urlCall;

        HttpResponse<String> response = null;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri)).GET()
                    .build();
            response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}

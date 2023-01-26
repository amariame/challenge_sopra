package com.miage;

public interface KartClientApi {
    public String ping();

    /**
     * Utilisez cette méthode pour obtenir votre identifiant !
     * @return Envoie l'identifiant de l'équipe à partir de son nom et du mot de passe associé.
     */
    String equipeId();

    String praticeId(int idBot);


    /**
     * Initialisation d'affrontement entre joueurs (VERSUS)
     * @param idEquipe Identifiant de l’équipe
     * @return Retourne l’identifiant de la partie à laquelle l’équipe doit participer "NA" si aucune partie n'est ouverte pour cette équipe.
     */
    String versus(String  idEquipe);

    /**
     * Initialisation d'affrontement contre les bots
     * @param idBot numéro de l’IA numéro de 1 à 8
     * @param idEquipe Identifiant de l’équipe
     * @return Crée une nouvelle partie contre une IA du niveau souhaité pour l’équipe concernée "NA" si la partie ne peut pas être créée
     */
    String practice(int idBot, String idEquipe);


    //deroulement de la partie


    /**
     * Indique si c'est au tour de l'équipe indiquée de jouer dans la partie
     * @param idEquipe Identifiant de l'Equipe
     * @param idPartie Identifant de la partie
     * @return "CANPLAY" si vous pouvez jouer
     *      * "CANTPLAY" si vous ne pouvez pas encore jouer
     *      * "RANKING" si vous avez terminé la partie mais qu'elle n'est pas finie (autres joueurs jouent encore)
     *      * "RANKED" si la partie est terminée
     *      * "CANCELLED" si la partie a été annulée
     *      * "DEFEAT" - Vous êtes mort !
     */
    String status(String idPartie, String idEquipe);


    /**
     * Retourne le plateau de jeu de la partie concernée.
     * La première équipe retournée est celle dont l'id est renseigné.
     * Les informations du joueur sont retournées dans la partie selfPlayer et ne sont plus présentes
     * dans la partie players.
     * @param idEquipe Identifiant de l'Equipe
     * @param idPartie Identifant de la partie
     * @return Renvoie, l'équipe de chaque joueur avec les informations sur chaque personnage.
     */
    String board(String idPartie, String idEquipe);


    /**
     * Joue un coup dans la partie concernée pour l’équipe indiquée.
     * @param idPartie Identifiant de la partie
     * @param idEquipe Identifiant de l'Equipe
     * @param action L'action de jeu parmi FORWARD, LEFT, RIGHT, BRAKE
     * @return "OK" si le coup est accepté
     * "FORBIDDEN" si le coup est refusé/interdit (entraine la défaite sur cette partie)
     * "NOTYET" si ce n'est pas au tour du joueur
     * "GAMEOVER" si le coup est joué sur une partie finie, quelque soit son état
     */
    String play(String idPartie, String idEquipe, String action);

    /**
     *Permet de récupérer les noms des adversaires (équipe ou bot) contre lequel vous jouez.
     * @param idPartie Identifiant de la partie
     * @param idEquipe Identifiant de l'Equipe
     * @return les noms des adversaires (équipe ou bot) contre lequel vous
     */
    String adversaires(String idPartie, String idEquipe);

}

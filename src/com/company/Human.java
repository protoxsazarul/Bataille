package com.company;

public class Human extends Player {
    public String getChoice() {
        System.out.println("Veuillez entrer les coordonnées de votre tir");
        String playerChoice = Constants.SCAN.nextLine().toUpperCase();
        System.out.println("COLONEL ON ENVOIE TOUT CE QU'ON A SUR " + playerChoice);
        return playerChoice;
    }
}

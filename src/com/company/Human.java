package com.company;

public class Human extends Player {
    public Human(){

    }
    public String getChoice() {
        String playerChoice = null;
        int row = -1;
        do {
            row = -1;
            System.out.println("A toi de jouer, choisis un emplacement (Ex : B5)");
            playerChoice = Constants.SCAN.nextLine().toUpperCase();
            if (playerChoice.length() > 0) {
                try {
                    row = Integer.parseInt(playerChoice.charAt(1) + "");
                } catch (Exception e) {
                    System.out.println("Coordonnées invalides, veuillez recommencer");
                    row = -1;
                }
                if (Constants.MAPPING.get(playerChoice.charAt(0) + "") == null) {
                    System.out.println("Coordonnées invalides, veuillez recommencer");
                }
            }
        }
        while ( playerChoice.length() == 0
                || Constants.MAPPING.get(playerChoice.charAt(0) + "") == null
                || row == -1
                || Constants.REVERSE_MAPPING.get(row) == null);
        System.out.println("COLONEL ON ENVOIE TOUT CE QU'ON A SUR " + playerChoice);
        return playerChoice;
        }
    }


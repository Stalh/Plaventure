package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

public class FileReader {
    protected World world;
    private Player player;
    private int camWidth;
    private int camHeight;
    private String level;
    private int time;

    public ArrayList<Nature> getElements() {
        return elements;
    }

    private ArrayList<Nature> elements = new ArrayList<>(68);
    public FileReader(World w){
        world = w;
        this.level = "";
    }
    //lire les fichiers level
    public void readFile(String level){
        elements = new ArrayList<>();
        this.level = level;
        try {
        boolean exists = Gdx.files.internal(level).exists();
        if(!exists){ //Erreur
                throw new Exception("Le fichier n'existe pas");
        }else{ //on lit le fichier
            FileHandle file = Gdx.files.internal(level);
            String text = file.readString();
            //System.out.println(text);
            String[] string  = text.split("\\n"); //on sépare les lignes

            String[] entiers = string[0].split("\\s"); //on prend la première ligne et on la sépare avec des espaces pour récuperer les entiers
            camWidth = Integer.parseInt(entiers[0]);
            camHeight = Integer.parseInt(entiers[1]);
            time = Integer.parseInt(entiers[2]);
            for(int i=1 ; i<string.length-1; i++ ){
                String[] type = string[i].split("");
                for (int j = 0; j<string[i].length(); j++){
                    Nature nature;
                    switch (type[j]) {
                        case "1":
                            nature = new Gemmes(world, 1, i, j , camWidth, camHeight);
                            elements.add(nature);
                            break;
                        case "2":
                            nature = new Gemmes(world, 2, i, j , camWidth, camHeight);
                            elements.add(nature);
                            break;
                        case "J":
                        case "K":
                        case "L":
                            nature =new Plateforme(world, type[j], i , j, camWidth, camHeight) ;
                            elements.add(nature);
                            break;
                        case "A":
                        case "B":
                        case "C":
                        case "D":
                        case "E":
                        case "F":
                        case "G":
                        case "H":
                        case "I":
                        case "Z":
                            nature = new Brick(world , type[j], i, j, camWidth, camHeight);
                            elements.add(nature);
                            break;
                        case "W":
                            nature = new Water(world, i, j, camWidth, camHeight);
                            elements.add(nature);
                            break;
                        case "P":
                            nature = new Player(world, i, j, camWidth, camHeight);
                            this.player = (Player) nature;
                            elements.add(nature);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Player getPlayer() {
        return player;
    }

    public int getCamWidth() {
        return camWidth;
    }


    public int getCamHeight(){
        return camHeight;
    }

    public int getTime() {
        return time;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}

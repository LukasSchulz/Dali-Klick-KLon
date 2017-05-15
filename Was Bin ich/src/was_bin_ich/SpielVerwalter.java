/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package was_bin_ich;

/**
 *
 * @author ed.zhang
 */
import java.util.Random;

public class SpielVerwalter {
    public boolean Tiles [][] = new boolean [5][4];
    private Random r = new Random();
    
    public SpielVerwalter(){
        for(int i = 0; i<Tiles.length; i++){
            for(int j = 0; j<Tiles[0].length; j++){
                Tiles[i][j] = true;
            }
        }
    }
    
    public void SelectRandomTile(){
        boolean con = true;
        while(con == true){
            if(Tiles[r.nextInt(Tiles.length)][r.nextInt(Tiles[0].length)] == true){
               Tiles[r.nextInt(Tiles.length)][r.nextInt(Tiles[0].length)] = false;
               con = false;
            }
        }
    }
    
    public boolean[][] Abfrage(){
        return Tiles;
    }
    
    public void Reset(){
        for(int i = 0; i<Tiles.length; i++){
            for(int j=0; j<Tiles[0].length; j++){
                Tiles[i][j] = true;
            }
        }
    }
    
}
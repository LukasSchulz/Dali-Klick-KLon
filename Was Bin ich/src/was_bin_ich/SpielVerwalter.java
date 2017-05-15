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
    public boolean[][] swTiles = new boolean [5][4];
    private Random r = new Random();
    private int lastx;
    private int lasty;
    
    public SpielVerwalter(){
        for(int i = 0; i<swTiles.length; i++){
            for(int j = 0; j<swTiles[0].length; j++){
                swTiles[i][j] = true;
            }
        }
    }
    
    public void SelectRandomTile(){
        int counter = 0;
        boolean con = true;
        while(con == true){
            int tempx = r.nextInt(swTiles.length);
            int tempy = r.nextInt(swTiles[0].length);
            if(swTiles[tempx][tempy] == true){
               swTiles[tempx][tempy] = false;
               lastx = tempx;
               lasty = tempy;
               con = false;
            }
            counter++;
            if(counter>19){
                con = false;
            }
        }
    }
    
    public boolean[][] Abfrage(){
        return swTiles;
    }
    
    
    
    public int getLastX(){
        return lastx;
    }
    
    public int getLastY(){
        return lasty;
    }
    
    public void reset(){
        for(int i = 0; i<swTiles.length; i++){
            for(int j = 0; j<swTiles[0].length; j++){
                swTiles[i][j] = true;
            }
        }
    }
    
}
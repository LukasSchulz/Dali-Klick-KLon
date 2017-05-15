/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package was_bin_ich;

import javafx.scene.image.ImageView;
import java.io.File;
import javafx.scene.image.Image;

/**
 *
 * @author ed.zhang
 */
public class TileVerwalter {
    private int xAbstand;
    private int yAbstand;
    private ImageView [][] tiles = new ImageView [5][4];
    private SpielVerwalter sw = new SpielVerwalter();
    
    public TileVerwalter(int x, int y, String path){
        xAbstand = x;
        yAbstand = y;
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        for(int i = 0; i<tiles.length; i++){
            for(int j = 0; j<tiles[0].length; j++){
                tiles[i][j] = new ImageView(image);
            }
        }
    }
    
    
}

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
    private ImageView[][] Tiles = new ImageView[5][4];
    private SpielVerwalter sw = new SpielVerwalter();

    public TileVerwalter(int x, int y, String path, int xRand, int yRand) {
        xAbstand = x;
        yAbstand = y;
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        for (int i = 0; i < Tiles.length; i++) {
            for (int j = 0; j < Tiles[0].length; j++) {
                Tiles[i][j] = new ImageView(image);
                Tiles[i][j].setTranslateX(xAbstand * i + xRand);
                Tiles[i][j].setTranslateY(yAbstand * j + yRand);
            }
        }
    }

    public ImageView OnButtonPress() {
        sw.SelectRandomTile();
        return Tiles[sw.getLastX()][sw.getLastY()];
    }

    public ImageView[][] getImgVArray() {
        return Tiles;
    }

    public void reset() {
        sw.reset();
    }
    
    public void aufdecken() {
        sw.aufdecken();
    }
}

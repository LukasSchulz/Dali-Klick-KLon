/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package was.bin.ich;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.image.Image;

/**
 *
 * @author lukas.schulz
 */
public class BilderMapGenerator {

    public BilderMapGenerator() {
    }
    
    HashMap generate(String name) throws FileNotFoundException, IOException {
        HashMap<Image, String> hm = new HashMap<Image, String>();
        File f = new File(name + "/list");
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);
        String text = bfr.readLine();
        while (text != null || text != "") {
            String[] teile = text.split("|");
            Image img = new Image(name + "/" + teile[0]);
            String resttext = "";
            for (int i = teile.length; i > 0; i--) {
                resttext += teile[i] + "|";
            }
            hm.put(img, resttext);
            text = bfr.readLine();
        }
        
        
        
        return hm;
    }
    
    ArrayList namen() throws FileNotFoundException, IOException{
        ArrayList<String> namen = new ArrayList<>();
        File f = new File("kathegorien/kathegorien");
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);
        String text = bfr.readLine();
        while (text != null || text != "") {
            namen.add(text);
            text = bfr.readLine();
        }
        
        return namen;
    }
    
}

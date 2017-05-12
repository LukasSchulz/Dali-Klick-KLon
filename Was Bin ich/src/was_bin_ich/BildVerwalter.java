/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package was_bin_ich;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author lukas.schulz
 */
public class BildVerwalter {
    
    private ImageView [] bilder;
    private ImageView tile;
    private int xAbstand;
    private int yAbstand;
    
    public BildVerwalter(ArrayList<String> bilderNamen,String path, String tilePath, int xAbstand, int yAbstand){
        tile = new ImageView(new Image(tilePath));
        BilderLaden(bilderNamen, path);
        this.xAbstand = xAbstand;
        this.yAbstand = yAbstand;
        
        
    }
    
    private void BilderLaden(ArrayList<String> bilderNamen, String path){
        Iterator<String> bilderNamenIter = bilderNamen.iterator();
        bilder = new ImageView [bilderNamen.size()];
        
        for(int i = 0; bilderNamenIter.hasNext(); i++){
            bilder[i] = new ImageView(new Image("src/"+path+"/"+bilderNamenIter.next()));
        }
    }
    
    public int getxAbstand(){
        return xAbstand;
    }
    
    public int getyAbstand(){
        return yAbstand;
    }
    

        
    }

    
  


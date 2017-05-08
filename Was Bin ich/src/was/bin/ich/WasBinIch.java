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
import java.util.Iterator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *
 * @author lukas.schulz
 */
public class WasBinIch extends Application {
    
    HashMap<Image,String> bilderhm = new HashMap<>();
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        loadArray(primaryStage);
        Group root = new Group();
        Scene theScene = new Scene(root);
        primaryStage.setScene(theScene);
        Canvas canvas = new Canvas(1000,500);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        primaryStage.show();
        
        
        
        
        
        
        
        
        
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public void loadArray(Stage theStage) throws FileNotFoundException, IOException{
        
        
        /**
         * Hab was mit ner Table ausprobiert
         * hat nicht funktioniert
         * Jetzt mach ich ne MenuButton
         */
        
        MenuButton menu;
        ArrayList<MenuItem> kategorien = new ArrayList<>();
        
        
        //sorry für die lokale Variable, wollte aber etwas testen......
        File f = new File("C:\\Users\\Lukas Schulz\\Documents\\NetBeansProjects\\Was_bin_ich\\Was Bin ich\\src\\kathegorien\\kathegorien");
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);
        String text = bfr.readLine();
        while(text!=""&&text!=null){
            kategorien.add(new MenuItem(text));
            text = bfr.readLine();
        }
        
        Iterator<MenuItem> kategorienIter = kategorien.iterator();
        MenuItem [] kath = new MenuItem [kategorien.size()];
        int zaeler = 0;
        while(kategorienIter.hasNext()){
            kath[zaeler]=kategorienIter.next();
            zaeler ++;
        }
        
        menu = new MenuButton("Kategorien",null,kath);
        
        
        theStage.show();
        
        
    }
    
    
    
    
    //Andere Methoden
    
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

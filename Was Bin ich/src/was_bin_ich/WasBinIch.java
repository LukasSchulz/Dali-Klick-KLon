/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package was_bin_ich;

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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;


/**
 *
 * @author lukas.schulz
 */
public class WasBinIch extends Application {
    
    HashMap<ImageView,String> bildHm = new HashMap<ImageView, String>();
    Random random = new Random();
    String kategorieName = "flaggen";
    private TileVerwalter tv = new TileVerwalter(256,180,"resources/tile.png",20,40);
    private String loesung = "";
    private int counter = 0;
    private int punkte = 100;
    private ImageView tempImgView;
    private boolean bildListe = false;
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        loadArray(primaryStage);
        
        primaryStage.show();
        
        
        
        
        
        
        
        
        
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public void loadArray(Stage theStage) throws Exception{
        Group root = new Group();
        Canvas canvas = new Canvas(250,50);
        root.getChildren().add(canvas);
        
        /**
         * Hab was mit ner Table ausprobiert
         * hat nicht funktioniert
         * Jetzt mach ich ne MenuButton
         */
        
        MenuButton menu;
        
        
        
        //sorry für die lokale Variable, wollte aber etwas testen......
        //temporär fehlt hier die ganze logik wegen einem zugriffsfehler
        /**
        File f = new File("/src/kathegorien/kathegorien");
        if(!f.exists()){
            f.mkdirs();
            f.createNewFile();
        }
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
        * **/
        
        //testlogikersatz
        MenuItem [] kath = new MenuItem[8];
        kath[0] = new MenuItem("Flaggen");
        kath[1] = new MenuItem("Marken");
        kath[2] = new MenuItem("Skylines");
        kath[3] = new MenuItem("Schauspieler");
        kath[4] = new MenuItem("Schauspielerinnen");
        kath[5] = new MenuItem("Sportler");
        kath[6] = new MenuItem("Wahrzeichen/Städte");
        kath[7] = new MenuItem("Cartoon Figuren");
        /**
         * Kategorien:
         * Flaggen
         * Marken
         * Skylines
         * Schauspieler
         * Schauspielerinnen
         * Sportler
         * Wahrzeichen/Städte
         * Cartoon Figuren
         * 
         */
        
        Button setKat = new Button("Start");
        setKat.setTranslateX(200);
        setKat.setTranslateY(20);
        root.getChildren().add(setKat);
        
        menu = new MenuButton("Kategorien",null,kath);
        menu.setTranslateX(10);
        menu.setTranslateY(20);
        
        TextField textField = new TextField();
        textField.setPromptText("Das Lösungswort hier eintippen.");
        textField.setMinWidth(500);
        textField.setMaxWidth(500);
        textField.setLayoutX(20);
        textField.setLayoutY(760);
        
        Button nextTile = new Button("Nächtes Feld aufdecken");
        nextTile.setLayoutX(530);
        nextTile.setLayoutY(760);
        
        Label counterLabel = new Label("Score: ");
        counterLabel.setTranslateX(700);
        counterLabel.setTranslateY(760);
        
        root.getChildren().add(menu);
        
        Scene pick = new Scene(root);
        theStage.setScene(pick);
        
        theStage.show();
        
        kath[0].setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){  
                kategorieName = "flaggen";
                System.out.println("Flaggen ausgewählt");
                menu.setText("Flaggen");
            }
        });
        
        kath[1].setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){        
                kategorieName = "marken";
                System.out.println("Marken ausgewählt");
                menu.setText("Marken");
            }
        });
        
        kath[2].setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){        
                kategorieName = "skylines";
                System.out.println("Skylines ausgewählt");
                menu.setText("Skylines");
            }
        });
        
        kath[3].setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                kategorieName = "schauspieler";
                System.out.println("Schauspieler ausgewählt");
                menu.setText("Schauspieler");
            }
        });
        
        kath[4].setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                kategorieName = "schauspielerinnen";
                System.out.println("Schauspielerinnen ausgewählt");
                menu.setText("Schauspielerinnen");
            }
        });
        
        kath[5].setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                kategorieName = "sportler";
                System.out.println("Sportler ausgewählt");
                menu.setText("Sportler");
            }
        });
        
        kath[6].setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                kategorieName = "wahrzeichen";
                System.out.println("Wahrzeichen ausgewählt");
                menu.setText("Wahrzeichen/Städte");
            }
        });
        
        kath[7].setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                kategorieName = "cartoon";
                System.out.println("Cartoon Figuren ausgewählt");
                menu.setText("Cartoon Figuren");
            }
        });
        
        setKat.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                
                
                root.getChildren().remove(menu);
                root.getChildren().remove(setKat);
                theStage.setX(100);
                theStage.setY(100);
                theStage.setHeight(900);
                theStage.setWidth(1320);
                root.getChildren().add(textField);
                root.getChildren().add(nextTile);
                root.getChildren().add(counterLabel);
                
                try {
                    bildHm = makeHM("src/"+kategorieName, getArrayListfromFile("src/"+kategorieName, "bilder"));
                    tempImgView = getRandomImage();
                    root.getChildren().add(tempImgView);
                } catch (IOException ex) {
                    System.out.println("File not found");
                }
                
                //schleife zum Tile Hinzufügen
                ImageView [] [] temp = tv.getImgVArray();
                
                for(int i = 0; i<temp.length;i++){
                    for(int j = 0; j<temp[0].length;j++){
                        root.getChildren().add(temp[i][j]);
                    }
                }
                
                
                
                
                
                
            }
        });
        
        textField.setOnAction(new EventHandler <ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                if(textField.getText().toLowerCase().contains(loesung.toLowerCase())){
                    if(!bildListe){
                        counter+=punkte;
                    }
                    tempImgView.setImage(getRandomImage().getImage());
                    
                    counterLabel.setText("Score: "+counter);
                    if(bildListe){
                        counterLabel.setText(counterLabel.getText()+" Sorry, keine weiteren Bilder mehr verfügbar");
                    }
                    punkte = 100;
                    System.out.println("Richtig");
                }
                else{
                    if(punkte>=0){
                        punkte-=10;
                    }
                }
                System.out.println("EnterDetected");
            }
        });
        
        
        nextTile.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
            }
        });
        
        
        
    }

        
        
        
        
        
        
    
    
    
    
    
    //Andere Methoden
    

    
    ArrayList getArrayListfromFile(String path, String name) throws FileNotFoundException, IOException{
        ArrayList namen = new ArrayList<>();
        File a = new File(path);
        if(!a.exists()){
            a.mkdirs();
            
            System.out.println("Folder created");
        }
        File f = new File(path+"/"+name);
        if(!f.exists()){
            f.createNewFile();
        }
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);
        String text = bfr.readLine();
        while (text != null && text != "") {
            namen.add(text);
            
            text = bfr.readLine();
        }
        
        return namen;
    }
    
    private HashMap<ImageView,String> makeHM(String path, ArrayList filesNames) throws IOException{
        HashMap<ImageView,String> hm = new HashMap<>();
        Iterator iter = filesNames.iterator();
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        
        while(iter.hasNext()){
            String fileName = (String) iter.next();
            System.out.println(fileName);
            System.out.println(path);
            File img = new File((path+"/")+fileName+".png");
            if(!img.exists()){
                img.createNewFile();
            }
            
            File file = new File(path+"/"+fileName+".png");
            loesung = fileName;
            Image image = new Image(file.toURI().toString(),1280, 720, true, false);
            ImageView imgv = new ImageView(image);
            imgv.setTranslateX(20);
            imgv.setTranslateY(20);
            hm.put(imgv, fileName);
        }
        
        
        return hm;
    }
    
    public ImageView getRandomImage(){
        if(!(bildHm.size()<=0)){
        int index = random.nextInt(bildHm.size());
                
        index-=1;
        Set<ImageView> tempSet = bildHm.keySet();
        Iterator<ImageView> tempIter = tempSet.iterator();
        while(tempIter.hasNext() && index>0){
            tempIter.next();
            index--;
        }
        
        ImageView temp = tempIter.next();
        loesung = bildHm.get(temp);
        bildHm.remove(temp);
        return temp;
        }
        else{
            ImageView temp = tempImgView;
            bildListe = true;
            return temp;
        }
        
    }
    
    
    
    
    
    
    
}

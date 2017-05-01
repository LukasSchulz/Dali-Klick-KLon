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
        
        Scene pick = new Scene(new Group());
        
        TableView table = new TableView();
        table.getColumns().addAll(new TableColumn("Kathegorie"));
        table.setEditable(false);
        ObservableList<String> kathegorien = FXCollections.observableArrayList();
        File f = new File("C:\\Users\\Lukas Schulz\\Documents\\NetBeansProjects\\Was_bin_ich\\Was Bin ich\\src\\kathegorien\\kathegorien");
        FileReader fr = new FileReader(f);
        BufferedReader bfr = new BufferedReader(fr);
        String text = bfr.readLine();
        while(text!=""&&text!=null){
            kathegorien.add(text);
            text = bfr.readLine();
        }
        table.setItems(kathegorien);
        VBox vbox = new VBox();
        
        vbox.getChildren().addAll(table);
        ((Group) pick.getRoot()).getChildren().add(vbox);
        theStage.setScene(pick);
        theStage.show();
        
        
    }
    
}

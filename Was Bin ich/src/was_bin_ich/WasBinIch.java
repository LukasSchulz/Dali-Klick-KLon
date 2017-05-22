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

    HashMap<ImageView, String> bildHm = new HashMap<ImageView, String>();
    Random random = new Random();
    String kategorieName = "flaggen";
    private TileVerwalter tv = new TileVerwalter(256, 180, "src/resources/tile.png", 20, 20);
    private String loesung = "";
    private int counter = 0;
    private int punkte = 100;
    private ImageView tempImgView;
    private boolean bildListe = false;

    private int anzProgress = 0;
    private int gesAnzBilder = 0;

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

    public void loadArray(Stage theStage) throws Exception {
        Group root = new Group();
        Canvas canvas = new Canvas(250, 50);
        root.getChildren().add(canvas);

        /**
         * Hab was mit ner Table ausprobiert hat nicht funktioniert Jetzt mach
         * ich ne MenuButton
         */
        MenuButton menu;

        //sorry für die lokale Variable, wollte aber etwas testen......
        //temporär fehlt hier die ganze logik wegen einem zugriffsfehler
        /**
         * File f = new File("/src/kathegorien/kathegorien"); if(!f.exists()){
         * f.mkdirs(); f.createNewFile(); } FileReader fr = new FileReader(f);
         * BufferedReader bfr = new BufferedReader(fr); String text =
         * bfr.readLine(); while(text!=""&&text!=null){ kategorien.add(new
         * MenuItem(text)); text = bfr.readLine(); }
         *
         * Iterator<MenuItem> kategorienIter = kategorien.iterator(); MenuItem
         * [] kath = new MenuItem [kategorien.size()]; int zaeler = 0;
         * while(kategorienIter.hasNext()){ kath[zaeler]=kategorienIter.next();
         * zaeler ++; }
        * *
         */
        //testlogikersatz
        MenuItem[] kath = new MenuItem[8];
        kath[0] = new MenuItem("Flaggen");
        kath[1] = new MenuItem("Marken");
        kath[2] = new MenuItem("Skylines");
        kath[3] = new MenuItem("Schauspieler");
        kath[4] = new MenuItem("Schauspielerinnen");
        kath[5] = new MenuItem("Sportler");
        kath[6] = new MenuItem("Wahrzeichen/Städte");
        kath[7] = new MenuItem("Cartoon Figuren");
        /**
         * Kategorien: Flaggen Marken Skylines Schauspieler Schauspielerinnen
         * Sportler Wahrzeichen/Städte Cartoon Figuren
         *
         */

        Button setKat = new Button("Start");
        setKat.setTranslateX(200);
        setKat.setTranslateY(20);
        root.getChildren().add(setKat);

        Button helpButton = new Button("Hilfe");
        helpButton.setTranslateX(150);
        helpButton.setTranslateY(20);
        root.getChildren().add(helpButton);

        menu = new MenuButton("Kategorien", null, kath);
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

        Label counterLabel = new Label("Score: 0");
        counterLabel.setTranslateX(850);
        counterLabel.setTranslateY(760);

        Label progress = new Label("");
        progress.setTranslateX(1300);
        progress.setTranslateY(20);
        
        Button backToMenuButton = new Button("Zurück zum Menü");
        backToMenuButton.setLayoutX(700);
        backToMenuButton.setLayoutY(760);

        root.getChildren().add(menu);

        Scene pick = new Scene(root);
        theStage.setScene(pick);

        theStage.show();

        kath[0].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kategorieName = "flaggen";
                System.out.println("Flaggen ausgewählt");
                menu.setText("Flaggen");
            }
        });

        kath[1].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kategorieName = "marken";
                System.out.println("Marken ausgewählt");
                menu.setText("Marken");
            }
        });

        kath[2].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kategorieName = "skylines";
                System.out.println("Skylines ausgewählt");
                menu.setText("Skylines");
            }
        });

        kath[3].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kategorieName = "schauspieler";
                System.out.println("Schauspieler ausgewählt");
                menu.setText("Schauspieler");
            }
        });

        kath[4].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kategorieName = "schauspielerinnen";
                System.out.println("Schauspielerinnen ausgewählt");
                menu.setText("Schauspielerinnen");
            }
        });

        kath[5].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kategorieName = "sportler";
                System.out.println("Sportler ausgewählt");
                menu.setText("Sportler");
            }
        });

        kath[6].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kategorieName = "wahrzeichen";
                System.out.println("Wahrzeichen ausgewählt");
                menu.setText("Wahrzeichen/Städte");
            }
        });

        kath[7].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kategorieName = "cartoon";
                System.out.println("Cartoon Figuren ausgewählt");
                menu.setText("Cartoon Figuren");
            }
        });

        setKat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                root.getChildren().remove(menu);
                root.getChildren().remove(setKat);
                root.getChildren().remove(helpButton);
                theStage.setX(100);
                theStage.setY(100);
                theStage.setHeight(900);
                theStage.setWidth(1500);
                root.getChildren().add(textField);
                root.getChildren().add(nextTile);
                root.getChildren().add(counterLabel);
                root.getChildren().add(progress);
                root.getChildren().add(backToMenuButton);

                try {
                    bildHm = makeHM("src/" + kategorieName, getArrayListfromFile("src/" + kategorieName, "bilder"));
                    gesAnzBilder = bildHm.size();

                    //routine zum erstmaligen progressLabel setzen
                    progress.setText("");
                    for (int i = 1; i <= gesAnzBilder; i++) {
                        progress.setText(progress.getText() + "\nBild " + i + ": ");
                        if (i <= anzProgress) {
                            progress.setText(progress.getText() + " gelöst");
                        } else {
                            progress.setText(progress.getText() + " nicht gelöst");
                        }

                    }
        //routine Ende

                    tempImgView = getRandomImage();
                    root.getChildren().add(tempImgView);
                } catch (IOException ex) {
                    System.out.println("File not found");
                }

                //schleife zum Tile Hinzufügen
                ImageView[][] temp = tv.getImgVArray();

                for (int i = 0; i < temp.length; i++) {
                    for (int j = 0; j < temp[0].length; j++) {
                        root.getChildren().add(temp[i][j]);
                    }
                }

            }
        });
        
        
        backToMenuButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                counter = 0;
                anzProgress = 0;
                
                root.getChildren().clear();
                
                root.getChildren().add(menu);
                root.getChildren().add(setKat);
                root.getChildren().add(helpButton);
                theStage.setHeight(100);
                theStage.setWidth(300);
                theStage.setX(400);
                theStage.setY(400);
                
                counterLabel.setText("Score: 0");
                
                bildListe = false;
                
                
                
            }
        });
        
        
        
        
        

        Label hilfe = new Label("Hier das Spielprinzip:\n"
                + "Zuerst wird eine Kategorie gewählt.\n"
                + "Danach wird ein zufällig ausgewähltes Bild\n"
                + "dieser Katergorie verdeckt unter \"Karten\"\n"
                + "angezeigt.\n"
                + "Deine Aufgabe ist es nun das Objekt auf dem\n"
                + "Bild zu erraten und dabei möglichst wenige\n"
                + "Karten aufzudecken.\n"
                + "Je weniger Karten umso mehr Punkte.");
        hilfe.setTranslateX(10);
        hilfe.setTranslateY(10);
        Button closeHelpButton = new Button("Schließen");
        closeHelpButton.setTranslateX(100);
        closeHelpButton.setTranslateY(200);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().remove(menu);
                root.getChildren().remove(setKat);
                root.getChildren().remove(helpButton);
                root.getChildren().add(closeHelpButton);
                root.getChildren().add(hilfe);
                theStage.setHeight(280);
                theStage.setWidth(300);

            }
        });

        closeHelpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().add(menu);
                root.getChildren().add(setKat);
                root.getChildren().add(helpButton);
                root.getChildren().remove(closeHelpButton);
                root.getChildren().remove(hilfe);
                theStage.setHeight(100);

            }
        });

        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (textField.getText().toLowerCase().contains(loesung.toLowerCase())) {
                    if (!bildListe) {
                        counter += punkte;
                    }

                    //schleife zum Tile Hinzufügen
                    ImageView[][] temp = tv.getImgVArray();

                    for (int i = 0; i < temp.length; i++) {
                        for (int j = 0; j < temp[0].length; j++) {
                            File file = new File("src/resources/tile.png");
                            Image image = new Image(file.toURI().toString());
                            temp[i][j].setImage(image);
                        }
                    }

                    tempImgView.setImage(getRandomImage().getImage());

                    counterLabel.setText("Score: " + counter);
                    if (bildListe) {
                        counterLabel.setText(counterLabel.getText() + "   Sorry, keine weiteren Bilder mehr verfügbar");
                    }
                    punkte = 100;

                    System.out.println("Richtig");
                    tv.reset();
                    anzProgress++;
                } else {
                    if (punkte >= 0) {
                        punkte -= 10;
                    }
                }
                textField.setText("");
                System.out.println("EnterDetected");

                progress.setText("");
                for (int i = 1; i <= gesAnzBilder; i++) {
                    progress.setText(progress.getText() + "\nBild " + i + ": ");
                    if (i <= anzProgress) {
                        progress.setText(progress.getText() + " gelöst");
                    } else {
                        progress.setText(progress.getText() + " nicht gelöst");
                    }

                }

            }
        });

        nextTile.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                File file = new File("src/resources/tile2.png");
                Image image = new Image(file.toURI().toString());
                tv.OnButtonPress().setImage(image);
                if (punkte >= 0) {
                    punkte -= 5;
                }
            }
        });

    }

    //Andere Methoden
    ArrayList getArrayListfromFile(String path, String name) throws FileNotFoundException, IOException {
        ArrayList namen = new ArrayList<>();
        File a = new File(path);
        if (!a.exists()) {
            a.mkdirs();

            System.out.println("Folder created");
        }
        File f = new File(path + "/" + name);
        if (!f.exists()) {
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

    private HashMap<ImageView, String> makeHM(String path, ArrayList filesNames) throws IOException {
        HashMap<ImageView, String> hm = new HashMap<>();
        Iterator iter = filesNames.iterator();
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }

        while (iter.hasNext()) {
            String fileName = (String) iter.next();
            System.out.println(fileName);
            System.out.println(path);
            File img = new File((path + "/") + fileName + ".png");
            if (!img.exists()) {
                img.createNewFile();
            }

            File file = new File(path + "/" + fileName + ".png");
            loesung = fileName;
            Image image = new Image(file.toURI().toString(), 1280, 720, true, false);
            ImageView imgv = new ImageView(image);
            imgv.setTranslateX(20);
            imgv.setTranslateY(20);
            hm.put(imgv, fileName);
        }

        return hm;
    }

    public ImageView getRandomImage() {
        if (!(bildHm.size() <= 0)) {
            int index = random.nextInt(bildHm.size());

            index -= 1;
            Set<ImageView> tempSet = bildHm.keySet();
            Iterator<ImageView> tempIter = tempSet.iterator();
            while (tempIter.hasNext() && index > 0) {
                tempIter.next();
                index--;
            }

            ImageView temp = tempIter.next();
            loesung = bildHm.get(temp);
            bildHm.remove(temp);
            return temp;
        } else {
            ImageView temp = tempImgView;
            bildListe = true;
            return temp;
        }

    }

}

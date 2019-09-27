package com.company.CityLodge.View;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ImportFile {
    private final Stage stage = new Stage();
    public ImportFile()
    {
        Button importFile = new Button("Click here to add file");
        importFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fc = new FileChooser();
                fc.setTitle("Choose a txt file");
                fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text","*.txt"));

                File file = fc.showOpenDialog(stage);
                // Here if do not choose a file， it will be a null file
                try {
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    String str = null;
                    while ((str = br.readLine()) != null) {

                    }
                    br.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        Button mainPage = new Button(" Back ");
        mainPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();

                CityLodgeApp cityLodgeApp = new CityLodgeApp();
                Stage ps = new Stage();
                cityLodgeApp.start(ps);
            }
        });

        GridPane grid = new GridPane();
        grid.add(importFile,0,0);
        grid.add(mainPage,0,1);
        grid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid);

        stage.setScene(scene);
        stage.setTitle("Import rooms from file");
        stage.setWidth(800);
        stage.setHeight(400);
        stage.show();
    }
}

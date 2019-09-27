package com.company.CityLodge.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class ExportFile {

    private final Stage stage = new Stage();
    public ExportFile(){
        Button exportFile = new Button("Click here to export data");
        exportFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fc = new FileChooser();
                fc.setTitle("Choose a txt file");
                fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text","*.txt"));
                fc.setInitialFileName("CityLodgeData");
                File file = fc.showSaveDialog(stage);
                try {
                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

                    outputStreamWriter.write("Test");
                    outputStreamWriter.close();
                    fileOutputStream.close();

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
        grid.add(exportFile,0,0);
        grid.add(mainPage,0,1);
        grid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.setTitle("Export rooms to txt");
        stage.setWidth(800);
        stage.setHeight(400);
        stage.show();
    }
}

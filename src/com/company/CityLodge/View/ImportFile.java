package com.company.CityLodge.View;


import com.company.CityLodge.Controller.InputHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ImportFile {
    private final Stage stage = new Stage();

    public ImportFile() {
        Button importFile = new Button("Click here to add file");
        importFile.setOnAction(new InputHandler(stage));

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
        grid.add(importFile, 0, 0);
        grid.add(mainPage, 0, 1);
        grid.setAlignment(Pos.CENTER);
        Scene scene = new Scene(grid);

        stage.setScene(scene);
        stage.setTitle("Import rooms from file");
        stage.setWidth(800);
        stage.setHeight(400);
        stage.show();
    }
}

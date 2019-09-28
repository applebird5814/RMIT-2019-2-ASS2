
package com.company.CityLodge.View;

import com.company.CityLodge.Controller.ExportHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class ExportFile {

    private final Stage stage = new Stage();
    public ExportFile(){
        Button exportFile = new Button("Click here to export data");
        exportFile.setOnAction(new ExportHandler(stage));

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

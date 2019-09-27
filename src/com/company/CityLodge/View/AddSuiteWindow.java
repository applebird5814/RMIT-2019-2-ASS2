package com.company.CityLodge.View;

import com.company.CityLodge.Controller.AddSuiteHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;
import com.company.CityLodge.Model.*;
public class AddSuiteWindow {
    private final Stage stage = new Stage();
    private String feature;
    private String roomID;
    private int year;
    private int month;
    private int day;

    public AddSuiteWindow()
    {
        Label lable1 = new Label("Room ID(It should be 3 digits)");
        TextField textArea1 = new TextField("111");
        textArea1.setTextFormatter(new TextFormatter<String>(new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change change) {
                if(change.getControlNewText().matches("[0-9]{0,3}"))
                {
                    return change;
                }
                return null;
            }
        }));

        Label lable2 = new Label("Feature(No more than 20 words)");
        TextField textArea2 = new TextField("Feature");

        Label lable3 = new Label("Last maintenance date");
        TextField textArea3 = new TextField("2019-09-23");
        textArea3.setTextFormatter(new TextFormatter<String>(new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change change) {
                if(change.getControlNewText().matches("[0-9]{0,4}[-]?[0-9]{0,2}[-]?[0-9]{0,2}"))
                {
                    return change;
                }
                return null;
            }
        }));

        Button submit = new Button("Submit");
        AddSuiteHandler addSuiteHandler = new AddSuiteHandler(textArea1,textArea2,textArea3);
        submit.setOnAction(addSuiteHandler);

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
        grid.add(lable1,0,0);
        grid.add(textArea1,2,0);
        grid.add(lable2,0,1);
        grid.add(textArea2,2,1);
        grid.add(lable3,0,2);
        grid.add(textArea3,2,2);
        grid.add(submit,1,3);
        grid.add(mainPage,1,5);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid);

        stage.setScene(scene);
        stage.setTitle("Add a standard Room");
        stage.setWidth(800);
        stage.setHeight(400);
        stage.show();
    }
}

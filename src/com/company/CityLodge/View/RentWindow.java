package com.company.CityLodge.View;

import com.company.CityLodge.Controller.Controller;
import com.company.CityLodge.Controller.RentHandler;
import com.company.CityLodge.Model.Room;
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

public class RentWindow {
    private final Stage stage = new Stage();

    public RentWindow(Room room)
    {
        Label lable2 = new Label("Customer ID");
        TextField textArea2 = new TextField("CUS_001");

        Label lable3 = new Label("Start Date of rent");
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

        Label lable4 = new Label("Days of rent");
        TextField textArea4 = new TextField("5");
        textArea4.setTextFormatter(new TextFormatter<String>(new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change change) {
                if(change.getControlNewText().matches("[0-9]{0,2}"))
                {
                    return change;
                }
                return null;
            }
        }));

        Button submit = new Button("Submit");
        Controller controller = CityLodgeApp.getController();
        RentHandler rentHandler = new RentHandler(controller.getHotel().findRoom(room.getRoomId()),textArea2,textArea3,textArea4);
        submit.setOnAction(rentHandler);

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
        grid.add(lable2,0,1);
        grid.add(textArea2,2,1);
        grid.add(lable3,0,2);
        grid.add(textArea3,2,2);
        grid.add(lable4,0,3);
        grid.add(textArea4,2,3);
        grid.add(submit,1,4);
        grid.add(mainPage,1,5);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid);

        stage.setScene(scene);
        stage.setTitle("Rent a room");
        stage.setWidth(800);
        stage.setHeight(400);
        stage.show();
    }

}

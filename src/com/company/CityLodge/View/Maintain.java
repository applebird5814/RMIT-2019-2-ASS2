package com.company.CityLodge.View;

import com.company.CityLodge.Controller.Controller;
import com.company.CityLodge.Controller.MaintainHandler;
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

public class Maintain {
        private final Stage stage = new Stage();

        public Maintain(Room room)
        {
            Label lable3 = new Label("Start Date");
            TextField textArea3 = new TextField("2019-09-20");
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
            Controller controller = CityLodgeApp.getController();
            MaintainHandler maintainHandler = new MaintainHandler(controller.getHotel().findRoom(room.getRoomId()));
            submit.setOnAction(maintainHandler);

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
            grid.add(lable3,0,2);
            grid.add(textArea3,2,2);
            grid.add(submit,1,4);
            grid.add(mainPage,1,5);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setAlignment(Pos.CENTER);

            Scene scene = new Scene(grid);

            stage.setScene(scene);
            stage.setTitle("Start Maintenance");
            stage.setWidth(800);
            stage.setHeight(400);
            stage.show();
        }
}

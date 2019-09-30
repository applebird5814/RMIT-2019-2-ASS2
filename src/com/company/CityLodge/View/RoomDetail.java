package com.company.CityLodge.View;

import com.company.CityLodge.JavaDatabase;
import com.company.CityLodge.Model.Room;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class RoomDetail {
    private final Stage stage = new Stage();

    public RoomDetail(Room room) {
        JavaDatabase database = new JavaDatabase();

        //Image(Large)
        String tempStr;
        tempStr = "images/" + database.imageName(room.getRoomId());
        File file = new File(tempStr);
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);

        //Records
        Text record = new Text(room.showAllRecord());

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(250, 200);
        scrollPane.setContent(record);

        //Buttons

        Button rent = new Button("Rent This Room");
        rent.setMaxWidth(150);
        rent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (room.getStates() == 1) {
                    stage.close();
                    RentWindow rentWindow = new RentWindow(room);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("This room is not Available for rent");
                    alert.show();
                }
            }
        });

        Button returnRoom = new Button("Return This Room");
        returnRoom.setMaxWidth(150);
        returnRoom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (room.getStates() == 0) {
                    stage.close();
                    ReturnWindow returnWindow = new ReturnWindow(room);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("This room is not rented.");
                    alert.show();
                }
            }
        });

        Button maintenance = new Button("Start A Maintenance");
        maintenance.setMaxWidth(150);
        maintenance.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (room.getStates() == 1) {
                    stage.close();
                    Maintain maintain = new Maintain(room);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("This room is not Available for maintenance");
                    alert.show();
                }
            }
        });

        Button completeMain = new Button("Complete Maintenance");
        completeMain.setMaxWidth(150);
        completeMain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (room.getStates() == -1) {
                    stage.close();
                    CompleteMaintain completeMaintain = new CompleteMaintain(room);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("This room is not being maintenance");
                    alert.show();
                }
            }
        });
        Button back = new Button("Back to main page");
        back.setMaxWidth(150);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();

                CityLodgeApp cityLodgeApp = new CityLodgeApp();
                Stage ps = new Stage();
                cityLodgeApp.start(ps);
            }
        });

        VBox vBox = new VBox();
        vBox.setPrefSize(150, 200);
        vBox.setSpacing(20.0);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(rent, returnRoom, maintenance, completeMain, back);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(imageView, 0, 0);
        grid.add(scrollPane, 1, 0);
        grid.add(vBox, 2, 0);

        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.setTitle("Room " + room.getRoomId() + " Detail");
        stage.setWidth(800);
        stage.setHeight(400);
        stage.show();
    }
}

package com.company.CityLodge.View;

import com.company.CityLodge.Controller.Controller;
import com.company.CityLodge.Model.Room;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;


public class CityLodgeApp extends Application {
    private static Controller controller = new Controller();

    public static void main(String[] args) {
        launch(args);
    }

    public static Controller getController() {
        return controller;
    }

    @Override
    public void start(Stage primaryStage) {

        MenuItem addStandard = new MenuItem("Adding a standard room");
        addStandard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddStandardRoomWindow standardRoomWindow = new AddStandardRoomWindow();
                primaryStage.close();
            }
        });

        MenuItem addSuite = new MenuItem("Adding a suite");
        addSuite.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AddSuiteWindow suiteWindow = new AddSuiteWindow();
                primaryStage.close();
            }
        });

        Menu addRoom = new Menu("Adding new room");
        addRoom.getItems().addAll(addStandard, addSuite);

        MenuItem importFile = new MenuItem("Click here to import a txt file");
        importFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ImportFile inFile = new ImportFile();
                primaryStage.close();
            }
        });

        Menu indata = new Menu("Import room data");
        indata.getItems().addAll(importFile);

        MenuItem exportFile = new MenuItem("Click here to store the data in a txt file");
        exportFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ExportFile exFile = new ExportFile();
                primaryStage.close();
            }
        });

        Menu exdata = new Menu("Export room data");
        exdata.getItems().addAll(exportFile);

        MenuItem quitProgram = new MenuItem("Click here to quit");
        quitProgram.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        Menu quit = new Menu("Quit the program");
        quit.getItems().addAll(quitProgram);

        MenuBar menubar = new MenuBar();
        menubar.getMenus().addAll(addRoom, indata, exdata, quit);

        int numOfRooms = controller.getHotel().getNumOfRooms();

        VBox vBox = new VBox();

        ScrollPane scrollPane = new ScrollPane();
        //scrollPane.setPrefSize(400,300);

        for (int i = 0; i < numOfRooms; i++) {
            String tempStr;
            int tempInt;

            Text title = new Text();
            Text feature = new Text();
            Text numOfBed = new Text();
            Text price = new Text();
            Button rent = new Button("Room Detail");
            final Room room = controller.getHotel().getRoom(i);
            rent.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    RoomDetail roomDetail = new RoomDetail(room);
                    primaryStage.close();
                }
            });

            tempStr = "images/" + controller.getDatabase().imageName(controller.getHotel().getRoom(i).getRoomId());
            File file = new File(tempStr);
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);

            tempStr = "Room:" + controller.getHotel().getRoom(i).getRoomId();
            title.setText(tempStr);

            tempStr = "Room Feature:\n" + controller.getHotel().getRoom(i).getFeature();
            feature.setText(tempStr);

            tempInt = controller.getHotel().getRoom(i).getNumOfBed();
            tempStr = "Bedrooms:" + Integer.toString(tempInt);
            numOfBed.setText(tempStr);

            tempInt = controller.getHotel().getRoom(i).getPrice(controller.getHotel().getRoom(i).getNumOfBed());
            tempStr = "Price:" + Integer.toString(tempInt);
            price.setText(tempStr);

            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setPrefSize(390, 110);
            anchorPane.getChildren().addAll(imageView, title, feature, numOfBed, price, rent);
            AnchorPane.setTopAnchor(imageView, 5.0);
            AnchorPane.setLeftAnchor(imageView, 5.0);
            AnchorPane.setTopAnchor(title, 20.0);
            AnchorPane.setLeftAnchor(title, 125.0);
            AnchorPane.setTopAnchor(feature, 50.0);
            AnchorPane.setLeftAnchor(feature, 125.0);
            AnchorPane.setTopAnchor(numOfBed, 20.0);
            AnchorPane.setRightAnchor(numOfBed, 82.0);
            AnchorPane.setTopAnchor(price, 50.0);
            AnchorPane.setRightAnchor(price, 100.0);
            AnchorPane.setTopAnchor(rent, 80.0);
            AnchorPane.setRightAnchor(rent, 50.0);

            vBox.getChildren().add(anchorPane);
        }

        scrollPane.setContent(vBox);

        AnchorPane anchor = new AnchorPane();
        anchor.getChildren().add(menubar);
        anchor.getChildren().add(scrollPane);

        AnchorPane.setTopAnchor(scrollPane, 50.0);
        AnchorPane.setLeftAnchor(scrollPane, 100.0);
        Scene scene = new Scene(anchor);

        scrollPane.prefHeightProperty().bind(primaryStage.heightProperty().subtract(100));
        scrollPane.prefWidthProperty().bind(primaryStage.widthProperty().subtract(200));
        menubar.prefWidthProperty().bind(primaryStage.widthProperty());

        primaryStage.setScene(scene);
        primaryStage.setTitle("CityLodgeApplication");
        primaryStage.setHeight(400);
        primaryStage.setWidth(600);
        primaryStage.show();

    }

}

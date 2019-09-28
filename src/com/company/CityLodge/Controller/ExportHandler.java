package com.company.CityLodge.Controller;

import com.company.CityLodge.View.CityLodgeApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class ExportHandler implements EventHandler<ActionEvent> {
    private Stage stage;
    public ExportHandler(Stage stage) {
        this.stage=stage;
    }

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

            outputStreamWriter.write(formatString());
            outputStreamWriter.close();
            fileOutputStream.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String formatString()
    {
        String s = "";
        Controller controller = CityLodgeApp.getController();
        for(int i=0;i<controller.getHotel().getNumOfRooms();i++)
        {
            s=s+controller.getHotel().getRoom(i).toString()+"\n";
            s=s+controller.getHotel().getRoom(i).exportRecord();
        }
        return s;
    }
}

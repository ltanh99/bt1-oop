package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class add {

    @FXML
    private TextField ta;
    @FXML
    private TextField tv;
    @FXML
    private Label result;
    public void addWord(ActionEvent event) throws IOException {
        String taNew =  ta.getText();
        String tvNew =  tv.getText();
        Dictionary.getArray().add(new Word(taNew,tvNew));
        DictionaryManagement.dictionaryExportToFile(Dictionary.getArray());
        System.out.println("Done");
        ta.setText("");
        tv.setText("");
        result.setText("Done");
    }

}

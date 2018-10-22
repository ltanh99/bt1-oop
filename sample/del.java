package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class del {
    @FXML
    private TextField ta;
    @FXML
    private Label result;
    public void delWord(javafx.event.ActionEvent event) throws IOException {
        String taDel =  ta.getText();
        for(int i =0; i<Dictionary.getArray().size();i++){
            if(taDel.equals(Dictionary.getArray().get(i).getWord_target())){
                Dictionary.getArray().remove(Dictionary.getArray().get(i));
                result.setText("Done");
            }
        }
        DictionaryManagement.dictionaryExportToFile(Dictionary.getArray());
        System.out.println("Done");
        ta.setText("");
    }
}
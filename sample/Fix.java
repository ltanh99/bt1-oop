package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Fix {
    @FXML
    private TextField ta_old;
    @FXML
    private TextField tv_old;
    @FXML
    private TextField ta_new;
    @FXML
    private TextField tv_new;



    public void fixWord(ActionEvent event) throws IOException {
            String taOld = ta_old.getText();
            String taNew = ta_new.getText();
            String tvOld = tv_old.getText();
            String tvNew = tv_new.getText();
            for (int i = 0; i < Dictionary.getArray().size(); i++) {
                if (taOld.equals(Dictionary.getArray().get(i).getWord_target())) {
                    Dictionary.getArray().get(i).setWord_target(taNew);}
                if(tvOld.equals(Dictionary.getArray().get(i).getWord_target())){
                    Dictionary.getArray().get(i).setWord_explain(tvNew) ;
                }
            }
            DictionaryManagement.dictionaryExportToFile(Dictionary.getArray());
            System.out.println("Done");
        }
}



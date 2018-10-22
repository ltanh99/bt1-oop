package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class Fix {
    @FXML
    private TextField ta_old;
    @FXML
    private TextField ta_new;
    @FXML
    private TextField tv_new;
    @FXML
    private Label result;
    public void fixWord(ActionEvent event) throws IOException {
        String taOld = ta_old.getText();
        String taNew = ta_new.getText();
        String tvNew = tv_new.getText();
        int test = 0;
        for (int i = 0; i < Dictionary.getArray().size(); i++)
        {
            if (taOld.equals(Dictionary.getArray().get(i).getWord_target()))
            {
                    if (tvNew != "") {
                        Dictionary.getArray().get(i).setWord_target(taNew);
                    } else {
                        Dictionary.getArray().get(i).setWord_explain(tvNew);
                    }
                test++;
                    result.setText("Done");
            }
        }
        if(test == 0)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Không tìm thấy từ đã nhập");
            a.show();
        }
        DictionaryManagement.dictionaryExportToFile(Dictionary.getArray());
        System.out.println("Done");
        ta_new.setText("");
        ta_old.setText("");
        tv_new.setText("");

    }
}
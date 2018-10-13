package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import  javafx.fxml.Initializable;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
        @FXML
        private TextField search;
        @FXML
        private TextArea trans;
        public void submit(ActionEvent event) {
            String Search = search.getText();
            String find = search(Dictionary.getArray(), Search);
            trans.setText(find);
        }
    ObservableList list= FXCollections.observableArrayList();
    @FXML
    private ListView<String> dictList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            data(Dictionary.getArray());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void data(ArrayList<Word> arr) throws FileNotFoundException {
        list.removeAll(list);
        Scanner sc = new Scanner(new File("C:\\Users\\Admin\\IdeaProjects\\javaApp\\src\\sample\\VN-EN Dict.txt")).useDelimiter("\\s*:\\s*");

        while (sc.hasNext()) {
            String en = sc.next();
            String vn = sc.nextLine();
            vn = vn.substring(3);
            arr.add(new Word(en, vn));
        }
        DictionaryManagement.sortDict(arr);
        for(int i=0;i<arr.size();i++)
        {
            list.add(arr.get(i).getWord_target());
        }
        dictList.getItems().addAll(list);
    }
    public String search(ArrayList<Word> arr,String find) {
        DictionaryManagement.sortDict(arr);
        int index=DictionaryManagement.bsearch(find,arr,0,arr.size());
        if(index < 0) {
            return "not found";
        } else {
            return arr.get(index).getWord_explain();
        }
    }
}


package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javafx.stage.StageStyle;
import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.modules.synthesis.Voice;
import marytts.signalproc.effects.*;

import javax.sound.sampled.AudioInputStream;

public class Controller implements Initializable {
        TextToSpeech ts = new TextToSpeech();
        Dictionary tmp = new Dictionary();

        boolean add= true;
        @FXML
        private TextField search;
        @FXML
        private TextArea trans;

        public void submit(ActionEvent event) {
            String Search = search.getText();
            String find = search(tmp.getArray(), Search);
            trans.setText(find);
        }
        public void speak(ActionEvent event)
        {
            ts.speak(search.getText(),2.0f,true,true);
        }
        public void openAdd(ActionEvent event) throws IOException {
            Parent addPage = FXMLLoader.load(getClass().getResource("add.fxml"));
            Scene addSc = new Scene(addPage);
            //Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Stage addStage = new Stage(StageStyle.DECORATED);
            addStage.setScene(addSc);
            addStage.setTitle("Add a word");
            addStage.show();

        }
    public void openDel(ActionEvent event) throws IOException {
        Parent delPage = FXMLLoader.load(getClass().getResource("del.fxml"));
        Scene delSc = new Scene(delPage);
        //Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage delStage = new Stage(StageStyle.DECORATED);
        delStage.setScene(delSc);
        delStage.setTitle("Delete a Word");
        delStage.show();
    }
    public void openFix(ActionEvent event) throws IOException {
        Parent fixPage = FXMLLoader.load(getClass().getResource("fix.fxml"));
        Scene fixSc = new Scene(fixPage);
        //Stage addStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage fixStage = new Stage(StageStyle.DECORATED);
        fixStage.setScene(fixSc);
        fixStage.setTitle("Fix a Word");
        fixStage.show();
    }
    ObservableList list= FXCollections.observableArrayList();


    @FXML
    public ListView<String> dictList;
    public ListView<String> getDictList()
    {
        return dictList;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            data(tmp.getArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void data(ArrayList<Word> arr) throws FileNotFoundException {

        list.removeAll(list);
        InputStream input = new FileInputStream("C:\\Users\\Admin\\IdeaProjects\\javaApp\\src\\txt\\VN-EN Dict.txt");
        //Scanner sc = new Scanner(new File("C:\\Users\\nguyen ngoc gioi\\Documents\\NetBeansProjects\\Dictionary1\\Dictionaries1.txt")).useDelimiter("\\s*:\\s*");
        Scanner sc = new Scanner(new InputStreamReader(input)).useDelimiter("\\s*:\\s*");
        while (sc.hasNext()) {
            String en = sc.next();
            String vn = sc.nextLine();
            vn = vn.substring(3);
            tmp.getArray().add(new Word(en, vn));
        }
        //DictionaryManagement.sortDict(arr);

            for (int i = 0; i < tmp.getArray().size(); i++) {
                list.add(tmp.getArray().get(i).getWord_target());
            }

        dictList.getItems().addAll(list);
        dictList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String old_val,
                 String new_val) -> {
                    trans.setText(search(tmp.getArray(), new_val));
                    search.setText(new_val);
                });

        search.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //output.setText(null);
                dictList.getItems().clear();
                if(search == null || search.getText().trim().length()==0)
                {
                    for(int i=0;i<tmp.getArray().size();i++)
                    {
                        dictList.getItems().add(tmp.getArray().get(i).getWord_target());
                    }
                }
                else
                {
                    for(int i=0;i<tmp.getArray().size();i++){
                        if((tmp.getArray().get(i).getWord_target()).indexOf(newValue.trim()) == 0)
                        {
                            dictList.getItems().add(tmp.getArray().get(i).getWord_target());
                        }
                    }
                }

            }

        });
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


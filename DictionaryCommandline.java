package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class DictionaryCommandline {
    public void showAllWords(ArrayList<Word> arr )
    {
        System.out.printf("|%-7s|%-90s|%-70s|\n","No","English","Vietnamese");
        int no = 1;
        for (Word element: arr){
            System.out.printf("|%-7d|%-90s|%-70s|\n", no,element.getWord_target(),element.getWord_explain());
            no++;
        }
    }
    public void dictionaryBasic() {
        DictionaryManagement.insertFromCommandline(Dictionary.arr);
        showAllWords(Dictionary.arr);
    }
    public void dictionaryAdvanced() throws IOException {
        //DictionaryManagement.insertFromCommandline(Dictionary.arr);
        DictionaryManagement.insertFromFile(Dictionary.arr);
        showAllWords(Dictionary.arr);
        DictionaryManagement.dictionaryLookup(Dictionary.arr);
    }
    public void dictionarySearcher()
    {

    }
}

package com.company;

import java.io.IOException;

public class test {

    public static void main(String[] args) throws IOException {
	// write your code here
        Dictionary dictionary = new Dictionary();
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();

//        dictionaryManagement.insertFromFile(dictionary.arr);
//        dictionaryCommandline.showAllWords(dictionary.arr);
        dictionaryCommandline.dictionaryAdvanced();
        dictionaryManagement.deleteAWord(dictionary.arr,48500);
    }
}

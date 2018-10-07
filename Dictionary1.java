/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary1;

import java.io.IOException;

/**
 *
 * @author Admin
 */
public class Dictionary1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException  {
        // TODO code application logic here
	// write your code here
        Dictionary dictionary = new Dictionary();
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
//         dictionaryManagement.insertFromFile(dictionary.arr);
//        dictionaryCommandline.showAllWords(dictionary.arr);
        dictionaryCommandline.dictionaryAdvanced();
        
    }
    }
    


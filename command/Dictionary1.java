/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary1;
import java.io.IOException;

/**
 *
 * @author nguyen ngoc gioi
 */
public class Dictionary1 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        dictionaryCommandline.dictionaryAdvanced(dictionary);
        DictionaryManagement.dictionaryExportToFile(Dictionary.arr);
    }
}

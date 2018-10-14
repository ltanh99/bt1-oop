/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary1;

/**
 *
 * @author nguyen ngoc gioi
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class DictionaryCommandline {
    public void showAllWords(ArrayList<Word> arr )
    {
        System.out.printf("|%-7s|%-90s|%-70s|\n","No","English","Vietnamese");
        DictionaryManagement.sortDict(arr);
        int no = 1;
        for (Word element: arr){
            System.out.printf("|%-7d|%-90s|%-70s|\n", no,element.getWord_target(),element.getWord_explain());
            no++;
        }
    }
    public void showWords(ArrayList<Word> arr){
        System.out.print("So luong tu: "+ arr.size()+"\n");
        System.out.print("10 tu bat ky: \n");
        System.out.printf("|%-7s|%-90s|%-70s|\n","No","English","Vietnamese");
        Random rd = new Random();
        int number;
        for(int i=1; i<=10; i++){
              number = 1+ rd.nextInt(arr.size());
            System.out.printf("|%-7d|%-90s|%-70s|\n", number,arr.get(number).getWord_target(),arr.get(number).getWord_explain());
        }
    }
    public void dictionaryBasic() {
        DictionaryManagement.insertFromCommandline(Dictionary.arr);
        showWords(Dictionary.arr);
    }
    public void dictionaryAdvanced(Dictionary dictionary) throws IOException{
        //DictionaryManagement.insertFromFile(Dictionary.arr);
        DictionaryManagement.insertFromCommandline(Dictionary.arr);
        //showWords(Dictionary.arr);
        while(true){
            System.out.print("Nhap 1: Tim tu.\nNhap 2: Them tu."
                    + "\nNhap 3: Xoa tu.\nNhap 4: Sua tu.\nNhap 0: Thoat.\n");
            Scanner sc = new Scanner(System.in);
            int  a= sc.nextInt();
            switch (a) {
                case 1:
                    DictionaryManagement.dictionaryLookup(Dictionary.arr);
                    break;
                case 2:
                    DictionaryManagement.addWord(Dictionary.arr);
                    break;
                case 3:
                    DictionaryManagement.deleteWord(Dictionary.arr);
                    break;

                case 4:
                    DictionaryManagement.fixWord(Dictionary.arr);
                    break;
                case 0:
                    break;
                default:
                    System.out.print("Nhap lai.\n");
                    dictionaryAdvanced(dictionary);
                    break;
            }if(a ==0) break;
        }
        
    }
    public void dictionarySearcher(ArrayList<Word> arr ) 
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Nhap tu can tim: ");
        String find= scan.nextLine();
        int no = 1;
        String array1[] = find.split("");
        int length = array1.length;
        System.out.println("length: " + length);
        for (Word element: arr){
            String array2[] = element.getWord_target().split("");
            String s = "";
            for(int i=0;i<length;i++)
           {
               s+=array2[i];
           }
            if(s.equals(find) ){
            System.out.printf("|%-7d|%-130s|%-70s|\n", no,element.getWord_target(),element.getWord_explain());}
            else no++;
        }
    }
 
}


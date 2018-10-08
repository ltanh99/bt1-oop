package dictionary1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
 public class DictionaryCommandline {
    public void showAllWords(ArrayList<Word> arr )
    {
        System.out.printf("|%-7s|%-130s|%-70s|\n","No","English","Vietnamese");
        DictionaryManagement.sortDict(arr);
        int no = 1;
        for (Word element: arr){
            System.out.printf("|%-7d|%-130s|%-70s|\n", no,element.getWord_target(),element.getWord_explain());
            no++;
        }
    }
    public void dictionaryBasic()  {
        DictionaryManagement.insertFromCommandline(Dictionary.arr);
        showAllWords(Dictionary.arr);
    }
    public void dictionaryAdvanced() throws IOException {
        
        DictionaryManagement.insertFromFile(Dictionary.arr);
        showAllWords(Dictionary.arr);
        DictionaryManagement.dictionaryLookup(Dictionary.arr);
        Scanner sc = new Scanner(System.in);
        
        System.out.println("nhap a");
        int a=sc.nextInt();
        if(a==1) DictionaryManagement.deleteWord(Dictionary.arr);
        else
        {
            if (a==2) DictionaryManagement.addWord(Dictionary.arr);
            
        }
        showAllWords(Dictionary.arr);
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
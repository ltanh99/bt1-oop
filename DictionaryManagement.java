package dictionary1;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Comparator;
 public class DictionaryManagement {
public static void insertFromCommandline(ArrayList<Word> arr)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Nhap Tieng Anh:");
        String ta = scan.nextLine();
        System.out.println("Nhap Tieng Viet:");
        String tv = scan.nextLine();
        arr.add(new Word(ta,tv));
    }
    public static void insertFromFile(ArrayList<Word> arr) throws IOException {
        Scanner sc = new Scanner(new File("C:\\Users\\Admin\\IdeaProjects\\java\\src\\VN-EN Dict.txt")).useDelimiter("\\s*:\\s*");
        while (sc.hasNext()) {
            String vn = sc.next();
            String en = sc.nextLine();
            en = en.substring(3);
            arr.add(new Word(en, vn));
        }
    }
    public static void dictionaryLookup(ArrayList<Word> arr)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Nhap tu can tim: ");
        String find= scan.nextLine();
        int no = 1;

        for (Word element: arr){
            if(element.getWord_target().contains(find)){
            System.out.printf("|%-7d|%-90s|%-70s|\n", no,element.getWord_target(),element.getWord_explain());}
            else no++;
        }
    }
    public static void sortDict(ArrayList<Word> arr)
    {
       Collections.sort(arr, Word.Word_targetComparator);
    }
    public Dictionary deleteWord(Dictionary dictionary)
    {
        System.out.println("Tu can xoa: ");
        Scanner scanner=new Scanner(System.in);
        String delete=scanner.nextLine();
        for(int i=0;i<dictionary.getArray().size();i++)
        {
            if(delete.equals(dictionary.getArray().get(i).getWord_target()))
            {
                dictionary.getArray().remove(dictionary.getArray().get(i));
            }
        }
        return dictionary;
    }
    public Dictionary addWord(Dictionary dictionary)
    {
        insertFromCommandline(dictionary.arr);
        return dictionary;
    }
    public Dictionary fixWord(Dictionary dictionary)
    {
        return dictionary;
    }
 }
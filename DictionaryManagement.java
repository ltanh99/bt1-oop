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
        if(arr.size()==0){
        Scanner sc = new Scanner(new File("C:\\Users\\Admin\\IdeaProjects\\java\\src\\test.txt")).useDelimiter("\\s*:\\s*");
        while (sc.hasNext()) {
            String vn = sc.next();
            String en = sc.nextLine();
            en = en.substring(1);
            
            arr.add(new Word(en, vn));
        }
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
            System.out.printf("|%-7d|%-130s|%-70s|\n", no,element.getWord_target(),element.getWord_explain());}
            else no++;
        }
    }
    public static void sortDict(ArrayList<Word> arr)
    {
       Collections.sort(arr, Word.Word_targetComparator);
    }
    public static void deleteWord(ArrayList<Word> arr)
    {
        System.out.println("Tu can xoa: ");
        Scanner scanner=new Scanner(System.in);
        String delete=scanner.nextLine();
        for(int i=0;i<arr.size();i++)
        {
            if(delete.equals(arr.get(i).getWord_target()))
            {
                arr.remove(arr.get(i));
               
            }
        }
        
    }
    public static void addWord(ArrayList<Word> arr)
    {
        insertFromCommandline(arr);
    }
    public static void dictionaryExportToFile() throws FileNotFoundException 
    {
        FileOutputStream fos= new FileOutputStream("C:\\Users\\Admin\\IdeaProjects\\java\\src\\output.txt",true);
            PrintWriter pw= new PrintWriter(fos);
            
            pw.close();

    }
 }
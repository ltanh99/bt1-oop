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
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.Collections;



public class DictionaryManagement {
    public static void insertFromCommandline(ArrayList<Word> arr)
    {
        Scanner scan = new Scanner(System.in);
        String ta = scan.nextLine();
        String tv = scan.nextLine();
        arr.add(new Word(ta,tv));
    }
    public static void insertFromFile(ArrayList<Word> arr) throws IOException {
        if(arr.isEmpty()){
            Scanner sc = new Scanner(new File("C:\\Users\\nguyen ngoc gioi\\Documents\\NetBeansProjects\\Dictionary1\\Dictionaries1.txt")).useDelimiter("\\s*:\\s*");
            // Scanner sc = new Scanner(new File("C:\\Users\\Admin\\IdeaProjects\\java\\src\\VN-EN Dict.txt")).useDelimiter("\\s*:\\s*");
            
            while (sc.hasNext()) {
                String en = sc.next();
                String vn = sc.nextLine();
                vn = vn.substring(3);
                arr.add(new Word(en, vn));
            }
        }
    }
    public static void dictionaryLookup(ArrayList<Word> arr)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Nhập từ cần tìm: ");
        String find= scan.nextLine();
        int no = 1;
        
        for (Word element: arr){
            if(element.getWord_target().contains(find)){
            System.out.printf("|%-7d|%-90s|%-70s|\n", no,element.getWord_target(),element.getWord_explain());}
            else no++;
        }if(no>arr.size()){
            System.out.print("Khong tim thay tu.\n");
        }
    }
    public static void sortDict(ArrayList<Word> arr)
    {
       Collections.sort(arr, Word.Word_targetComparator);
    }
    public static void deleteWord(ArrayList<Word> arr){
        System.out.print("Nhap tu can xoa: ");
        Scanner scanner = new Scanner(System.in);
        String delete = scanner.nextLine();
        for(int i =0; i<arr.size();i++){
            if(delete.equals(arr.get(i).getWord_target())){
                arr.remove(arr.get(i));
            }
        }
     
    }
    public static void addWord(ArrayList<Word> arr){
        System.out.print("Nhap tu can them: ");
        insertFromCommandline(arr);
    }
    public static void fixWord(ArrayList<Word> arr){
        Scanner scan = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        System.out.print("   Nhap tu can sua: ");
         String fix = scan.nextLine();
         for(int i=0; i<arr.size(); i++){
            if(fix.equals(arr.get(i).getWord_target())){
                System.out.print("   Tu ban dau: " +arr.get(i).getWord_target() +
                " : " + arr.get(i).getWord_explain()+"\n");
            }
        }
        System.out.print("   1: Sua tieng anh.\n   2.Sua nghia tieng viet.\n   0.Quay lai.\n");
        int a = scan.nextInt();
        switch (a) {
            case 1:
                System.out.print("      Tu tieng anh thay the: ");
                String ta = sc.nextLine();
             
                for(int i=0; i<arr.size(); i++){
                    if(fix.equals(arr.get(i).getWord_target())){
                        arr.get(i).setWord_target(ta);
                       
                        System.out.print("   Sua thanh: " +arr.get(i).getWord_target() +
                                " : " + arr.get(i).getWord_explain()+"\n");
                    }
                }   break;
            case 2:
                
                System.out.print("      Nghia tieng viet: ");
                String tv = sc.nextLine();
                for(int i=0; i<arr.size(); i++){
                    if(fix.equals(arr.get(i).getWord_target())){
                                               
                        arr.get(i).setWord_explain(tv) ;
                        System.out.print("Sua thanh: " +arr.get(i).getWord_target() +
                                " : " + arr.get(i).getWord_explain()+"\n");
                    }
                }   break;
            case 0:
                break;
            default:
                System.out.print("Nhap lai.\n");
                fixWord(arr);
                break;
        }
    }
    

    
    public void dictionaryExportToFile(ArrayList<Word> arr) throws IOException{
        insertFromFile(arr);
        
        sortDict(arr);
        
        try{
            File x = new File("Dictionaries2.txt");
            if(!x.exists()){
                x.createNewFile();
            }
            FileWriter file = new FileWriter(x);
            try (Writer out = new BufferedWriter(file)) {
              
                for(int i=0; i<arr.size(); i++){
                  
                    
                    out.write(arr.get(i).getWord_target()+" : ");
                    out.write(arr.get(i).getWord_explain()+"\n");
                    
                }
            }
            
        }catch(IOException e){
            
        }
    }
    
}

    



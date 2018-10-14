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
        System.out.print("Nhap tu tieng anh: ");
        String ta = scan.nextLine();
        System.out.print("Nhap nghia tieng viet: ");
        String tv = scan.nextLine();
        arr.add(new Word(ta,tv));
    }
    public static void insertFromFile(ArrayList<Word> arr) throws IOException {
        if(arr.isEmpty()){        
            Scanner sc = new Scanner(new File("C:\\Users\\nguyen ngoc gioi\\Documents\\NetBeansProjects\\Dictionary1\\dictionaries1.txt")).useDelimiter("\\s*:\\s*");
            //Scanner sc = new Scanner(new File("C:\\Users\\Admin\\IdeaProjects\\javaApp\\src\\sample\\VN-EN Dict.txt")).useDelimiter("\\s*:\\s*");

            while (sc.hasNext()) {
                String en = sc.next();
                String vn = sc.nextLine();
                vn = vn.substring(3);
                arr.add(new Word(en, vn));
            }
        }
    }
    public static void tach (ArrayList<Word> arr) throws IOException {
        insertFromFile(arr);
        int no=1;
        for (Word element : arr) {
            String array1[] = element.getWord_target().split("");

            int length = array1.length;
            String s="";
            for(int i=0;i<=length;i++)
            {
                if(",".equals(array1[i]))
                {
                    arr.add(new Word(s,element.getWord_explain()));
                }
                s+=array1[i];
            }
            no++;
        }
        dictionaryExportToFile(arr);
    }
    public static int bsearch(String word, ArrayList<Word> arr, int a, int b) {
        if(b <= a)
            return -1;

        if(b - a == 1)
            return arr.get(a).getWord_target().contains(word) ? a : -1;

        int pivot = (a + b)/2;
        if(word.compareTo(arr.get(pivot).getWord_target()) < 0) {
            return bsearch(word, arr, 0, pivot);
        } else if(word.compareTo(arr.get(pivot).getWord_target()) > 0) {
            return bsearch(word, arr, pivot, b);
        }
        return pivot;
    }

    public static void dictionaryLookup(ArrayList<Word> arr) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Nhap tu can tim: ");
        String find = scan.nextLine();
        DictionaryManagement.sortDict(arr);
        int index = bsearch(find, arr, 0, arr.size());
        if (index < 0) {
            System.out.println("not found");
        } else {
            System.out.printf("|%-7d|%-90s|%-70s|\n", index, arr.get(index).getWord_target(), arr.get(index).getWord_explain());
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
        System.out.print("Nhap tu can sua: ");
        String fix = scan.nextLine();
        boolean check = false;
        for(int i=0; i<arr.size(); i++){
            if(fix.equals(arr.get(i).getWord_target())){
                check = true;
                System.out.print("Tu ban dau: " +arr.get(i).getWord_target() +
                        " : " + arr.get(i).getWord_explain()+"\n");
            }
        }if(check ==false){
            System.out.print("Khong tim thay tu.\nBan muon them tu?\n");
            System.out.print("1.Them tu.\n0.Quay lai.\n");
            int x = sc.nextInt();
            if(x ==1){
                addWord(arr);
            }
                    
        }else{
            System.out.print("1: Sua tieng anh.\n2.Sua nghia tieng viet.\n0.Quay lai.\n");
            int a = sc.nextInt();
            switch (a) {
                case 1:
                    System.out.print("Tu tieng anh thay the: ");
                    String ta = scan.nextLine();

                    for(int i=0; i<arr.size(); i++){
                        if(fix.equals(arr.get(i).getWord_target())){
                            arr.get(i).setWord_target(ta);

                            System.out.print("Sua thanh: " +arr.get(i).getWord_target() +
                                    " : " + arr.get(i).getWord_explain()+"\n");
                        }
                    }   break;
                case 2:

                    System.out.print("Nghia tieng viet: ");
                    String tv = scan.nextLine();
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
    }



    public static void dictionaryExportToFile(ArrayList<Word> arr) throws IOException{
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
    //C:\Users\Admin\IdeaProjects\javaApp\src\sample\
}


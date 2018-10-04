package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
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
        System.out.print("Nhập từ cần tìm: ");
        String find= scan.nextLine();
        int no = 1;
        
        for (Word element: arr){
            if(element.getWord_target().contains(find)){
            System.out.printf("|%-7d|%-90s|%-70s|\n", no,element.getWord_target(),element.getWord_explain());}
            else no++;
        }
    }

}

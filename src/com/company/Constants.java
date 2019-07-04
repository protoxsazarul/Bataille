package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Constants {
    public static final Scanner SCAN = new Scanner(System.in);
    public static final Map<String, Integer> MAPPING = new HashMap<>();
    static  {
        MAPPING.put("A",0);
        MAPPING.put("B",1);
        MAPPING.put("C",2);
        MAPPING.put("D",3);
        MAPPING.put("E",4);
        MAPPING.put("F",5);
        MAPPING.put("G",6);
        MAPPING.put("H",7);
        MAPPING.put("I",8);
        MAPPING.put("J",9);

    }
    public static final  Map<Integer, String> REVERSE_MAPPING = new HashMap<>();
    static  {
        REVERSE_MAPPING.put(0,"A");
        REVERSE_MAPPING.put(1,"B");
        REVERSE_MAPPING.put(2,"C");
        REVERSE_MAPPING.put(3,"D");
        REVERSE_MAPPING.put(4,"E");
        REVERSE_MAPPING.put(5,"F");
        REVERSE_MAPPING.put(6,"G");
        REVERSE_MAPPING.put(7,"H");
        REVERSE_MAPPING.put(8,"I");
        REVERSE_MAPPING.put(9,"J");

    }
}
package org.example;

import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        StringBuilder reverseStr = new StringBuilder("");

        System.out.println("Enter a string: ");
        String word = in.next();

        for (int i = word.length(); i >= 1; i--){
            reverseStr.append(word.substring(i-1, i));
            if (i == 0) break;
            }
        System.out.println("The reversed string is: " + reverseStr);
    }
}

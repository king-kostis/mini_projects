package org.example;

import java.util.Scanner;

public class PalindromeCheck {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        StringBuilder num = new StringBuilder("");

        System.out.println("Enter a number: ");
        num.append(in.next());

        //Check if num equals reverseNum
        String isPalindrome = num.equals(num.reverse()) ? num.toString() + " is a Palindrome" : num.toString() + " is not a Palindrome";
        System.out.println(isPalindrome);
    }
}

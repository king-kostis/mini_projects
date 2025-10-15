package org.example;

import java.util.Scanner;

public class VowelCount {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int vowel = 0;
        int consonant = 0;

        System.out.println("Enter a word: ");
        String word = in.next();

        //Check if a character is a vowel
        for(int i = 0; i < word.length(); i++){
            if (word.charAt(i) == 'a' || word.charAt(i) == 'e' || word.charAt(i) == 'i' || word.charAt(i) == 'o' || word.charAt(i) == 'u'){
                vowel++;
            } else {
                consonant++;
            }
        }

        System.out.println("Vowels: " + vowel + "\n" + "Consonants: " + consonant);
    }
}

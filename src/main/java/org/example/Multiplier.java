package org.example;

import java.util.Scanner;

public class Multiplier {
    public static void main(String[] args){
        Scanner myObj = new Scanner(System.in);

        System.out.println("Input a number: ");
        int num = myObj.nextInt();

        for(int i = 1; i <= 10; i++){
            int mul = num * i;
            System.out.println(num + " x " + i + " = " + mul);
        }
    }
}

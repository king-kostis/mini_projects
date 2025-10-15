package org.example;

import java.util.Scanner;

public class BinaryAddition {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int i = 0;
        long remainder = 0;
        long sum[] = new long[20];

        //Enter first binary number
        System.out.println("Input first binary number: ");
        long bin1 = input.nextLong();

        //Enter second binary number
        System.out.println("Input second binary number: ");
        long bin2 = input.nextLong();


        long oldBin1 = bin1;
        long oldBin2 = bin2;

        //adding of binary digits
        while(bin1 != 0 || bin2 != 0){
            sum[i++] = ((bin1 % 10 + bin2 % 10 + remainder) % 2);// adds last numbers of the two binaries and assigns them to the array
            remainder = ((bin1 % 10 + bin2 % 10 + remainder) / 2);// gets the remainder from adding and holds it

            //remove last digit from binaries
            bin1 = bin1 / 2;
            bin2 = bin2 / 2;
        }

        //if the final reaminder is not equal to zero then add it to the array
        if (remainder != 0){
            sum[i++] = remainder;
        }

        //Go back to last digits position
        --i;

        //Print out the sum
        System.out.print("The sum of " + oldBin1 + " and " + oldBin2 + " is ");
        while(i >= 0){
            System.out.print(sum[i]);
            --i;
        }
        System.out.print("\n");
    }
}

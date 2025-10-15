package org.example;

import java.util.Scanner;

public class AreaCalculator{
    private static float pi = 3.142f;

    private static float circle(float radius){
        return (pi * radius * radius);
    }

    private static float square(float length){
        return (length * length);
    }

    private static float rectangle(float length, float breadth){
        return (length * breadth);
    }

    private static float sphere(float radius){
        return (4 * pi * radius);
    }

    private static float cube(float surfaceArea){
        return (6 * surfaceArea * surfaceArea);
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to the area calculator!");
        while(true){
            System.out.println("\nEnter 1 for circle\n" +
                    "Enter 2 for square\n" +
                    "Enter 3 for rectangle");

            int choice = Integer.parseInt(in.nextLine());

            if (choice == 1) {
                System.out.println("Enter the radius of the circle");
                float radius = Float.parseFloat(in.nextLine());

                System.out.println("The area of circle of radius "+ radius + "is " + circle(radius));
            } else if (choice == 2) {
                System.out.println("Enter the length of the square");
                float length = Float.parseFloat(in.nextLine());

                System.out.println("The area of square of side length "+ length + " is " + square(length));
            } else if (choice == 3) {
                System.out.println("Enter the length of the rectangle");
                float length = Float.parseFloat(in.nextLine());

                System.out.println("Enter the breadth of the rectangle");
                float breadth = Float.parseFloat(in.nextLine());

                System.out.println("The area of rectangle of length "+ length +
                        "and breadth "+breadth+ " is " + rectangle(length,breadth));
            } else {
                System.out.println("Invalid choice");
                break;
            }
        }
    }
}

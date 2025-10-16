package org.example.exercises.grocery;
import java.util.Scanner;

public class GroceryShopping{
    public static void main(String[] args){
        String[] items = new String[20];
        float[] prices = new float[20];
        int itemIdx = 0; // item index tracker
        int priceIdx = 0; // price index tracker

        items[itemIdx++] = "rice";   prices[priceIdx++] = 85.00f;
        items[itemIdx++] = "cooking oil";   prices[priceIdx++] = 45.00f;
        items[itemIdx++] = "sugar";   prices[priceIdx++] = 18.00f;
        items[itemIdx++] = "milk powder";   prices[priceIdx++] = 35.00f;
        items[itemIdx++] = "eggs";   prices[priceIdx++] = 55.00f;
        items[itemIdx++] = "bread";   prices[priceIdx++] = 20.00f;
        items[itemIdx++] = "tomatoes";   prices[priceIdx++] = 25.00f;
        items[itemIdx++] = "onions";   prices[priceIdx++] = 22.00f;
        items[itemIdx++] = "frozen chicken";   prices[priceIdx++] = 65.00f;
        items[itemIdx++] = "sardines";   prices[priceIdx++] = 12.00f;

        Scanner in = new Scanner(System.in);

        boolean isShopping = true;
        while(isShopping){
            System.out.println("\nEnter a grocery item  and its price to add to list");
            System.out.println("Enter 'exit' to end shopping");
            String input = in.nextLine(); //store item

            // Exits loop if user types in 'exit'
            if (input.equalsIgnoreCase("exit")){
                System.out.println("Grocery List Completed!");
                isShopping = false;
            }

            System.out.println("Enter the price of the item");
            float priceInput = in.nextFloat(); // store price of item
            in.nextLine();

            try{
                if(!(items[items.length - 1] == null)){ // Checks if grocery list is full
                    System.out.println("Sorry your grocery list is full");
                } else {
                    items[itemIdx++] = input.strip(); // adds item to the list
                    prices[priceIdx++] = priceInput; // adds price of items to the list
                    System.out.println("Item successfulluy added!");
                    continue;
                }

            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Caught Exception: " + e.getMessage());
            }
        }


        boolean isCheckingItem = true;
        while(isCheckingItem){
            System.out.println("\nEnter an item to check in grocery list");
            System.out.println("Enter 'finish' to end shopping");
            String itemToCheck = in.nextLine(); // stores the item enetered by the user to check for

            // Exits loop when the user enters 'finish'
            if(itemToCheck.equalsIgnoreCase("finish")){
                System.out.println("Shopping Complete!");
                isCheckingItem = false;
            }

            int itemToCheckIdx = -1; //If item is on the list the value changes
            try{
                //Iterates throgh items array to check for the item the user entered
                for(int i = 0; i < items.length; i++){
                    if(itemToCheck.equalsIgnoreCase(items[i])){
                        System.out.println("The item " + items[i] + " is in your grocery list");
                        itemToCheckIdx = i; // Changes from -1 to any number to show that the item is on the list
                        break;
                    }
                }

                if(itemToCheckIdx == -1){ // Checks if value change and throws a custom ItemNotFoundException.
                    throw new ItemNotFoundException("Sorry the item isn't on your grocery list");
                }
                //Catches exception and goes to next iteration of the loop
            } catch(ItemNotFoundException | ArrayIndexOutOfBoundsException e){
                System.out.println("Caught Exception: " + e.getMessage());
            }
        }

        //Calculates total price of all items
        float totalPrice = 0.00f;
        for(float price : prices){
            totalPrice += price;
        }
        System.out.println("\nThe final price for your groceries is " + totalPrice);
    }


}
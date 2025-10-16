package org.example.exercises.mood_tracker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoodTracker {
    private static boolean isMoodValid(Mood mood, List<Mood> moodList) throws InvalidMoodException{
        for(Mood tempMood : moodList){
            if(tempMood.equals(mood)) {
                throw new InvalidMoodException();
            }
        }
        return true;
    }

    //method to delete mood object in list by date
    private static void deleteByDate(String date, List<Mood> moodList){
        boolean isNotFound = true;
        for(Mood mood : moodList){
            if(mood.getDate().toString().equals(date)){
                moodList.remove(mood);
                isNotFound = false;
                continue;
            }
        }

        if(isNotFound){
            System.out.println("Mood not found in list");
        } else{
            System.out.println("Deletion successful");
        }
    }

    //method to delete mood by the name, date and time
    private static void deleteByName(String name, String date, String time, List<Mood> moodList){
        boolean isNotFound = true;
        for(Mood mood : moodList){
            if(mood.getName().equals(name) && mood.getDate().toString().equals(date) && mood.getTime().toString().equals(time) ){
                moodList.remove(mood);
                isNotFound = false;
            }
        }

        if(isNotFound) {
            System.out.println("Mood not found in list");
        } else {
            System.out.println("Deletion succesful");
        }
    }

    //method to edit mood notes
    private static void editMoodNotes(String name, String date, String time, List<Mood> moodList){
        Scanner in = new Scanner(System.in);
        boolean isNotFound = true;
        for(Mood mood : moodList){
            if(mood.getName().equals(name) && mood.getDate().toString().equals(date) && mood.getTime().toString().equals(time)){
                System.out.println("Add mood notes to mood: " + mood.getName());
                String moodNotes = in.nextLine();

                mood.setNotes(moodNotes.strip());
                System.out.println("Notes added successfully");
                isNotFound = false;
            }
        }

        if(isNotFound) System.out.println("Mood not found in list");
    }

    //method to search for mood by date
    private static void searchByDate(String date, List<Mood> moodList){
        boolean isNotFound = true;

        for(Mood mood : moodList){
            if(mood.getDate().toString().equals(date)){
                System.out.println(mood.toString());
                isNotFound = false;
            }
        }

        if(isNotFound) System.out.println("Mood not found in list");
    }

    //method to search for mood by name, date and time
    private static void searchByAll(String name, String date, String time, List<Mood> moodList){
        boolean isNotFound = true;

        for(Mood mood : moodList){
            if(mood.getName().equals(name) && mood.getDate().toString().equals(date) && mood.getTime().toString().equals(time)){
                System.out.println(mood.toString());
                isNotFound = false;
            }
        }

        if(isNotFound) System.out.println("Mood not found in list");
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        List<Mood> moodList = new ArrayList<>();


        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");//Time format
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss"); //Date format

        boolean running = true;
        while(running){
            System.out.println("\nPress 'a' to add mood\n" +
                    "'d' to delete mood(s)\n" +
                    "'e' to edit mood\n" +
                    "'s' to search for moods\n" +
                    "'M' to get all moods\n" +
                    "'w' to write the moods to a file\n" +
                    "Type 'Exit' to exit");

            String menuOption = in.nextLine();

            //Menu
            switch(menuOption){

                //Add Mood to list
                case "a":
                    Mood mood = new Mood();

                    //Prompt user to enter name
                    System.out.println("Enter the name of the mood");
                    String moodName = in.nextLine();

                    //Prompt user to enter date and convert to date object
                    System.out.println("Enter the date for the mood in the format dd/MM/yyyy");
                    String moodDateStr = in.nextLine();

                    //Prompt user to enter time and convert to time object
                    System.out.println("Enter the time for the mood in the format HH:mm:ss");
                    String moodTimeStr = in.nextLine();


                    try{
                        if(moodDateStr.isEmpty() && moodTimeStr.isEmpty()) { //Constructs mood object by only the name
                            mood.setName(moodName.toUpperCase());

                        } else if (moodTimeStr.isEmpty()){ //Constructs mood object by the name and date
                            mood.setName(moodName.toUpperCase());

                            LocalDate moodDate = LocalDate.parse(moodDateStr, dateFormat);
                            mood.setDate(moodDate);

                        } else { //Constructs mood object by name, date and time
                            mood.setName(moodName.toUpperCase());
                            LocalDate moodDate = LocalDate.parse(moodDateStr, dateFormat);
                            mood.setDate(moodDate);

                            LocalTime moodTime = LocalTime.parse(moodTimeStr, timeFormat);
                            mood.setTime(moodTime);
                        }

                        if(isMoodValid(mood, moodList)) {
                            moodList.add(mood);//Checks if mood already exists
                            System.out.println("Mood added successfully");
                        } else {
                            System.out.println("Could not add mood");
                        }

                    } catch (InvalidMoodException e){
                        System.out.println("Invalid mood entry. Mood may already exist");
                    } catch (DateTimeParseException dpe){
                        System.out.println("Error ocurred in date or time parsing: " + dpe.getMessage());
                    }

                    break;

                    //Delete from mood list
                case "d":
                    System.out.println("\nEnter 1 to delete moods on a particular date " +
                            "\nEnter 2 to delete mood by name, date and time");
                    String deleteOption = in.nextLine();

                    try {
                        if (deleteOption.equals("1")) {//Deletes by date
                            System.out.println("Enter a date in the format dd/MM/yyyy");
                            String deleteDateStr = in.nextLine();
                            LocalDate deleteDate = LocalDate.parse(deleteDateStr, dateFormat);

                            deleteByDate(deleteDate.toString(), moodList);

                        } else if (deleteOption.equals("2")) {//Deletes by name, date and time

                            //Prompts user to enter name of mood to delete
                            System.out.println("Enter a name");
                            String deleteName = in.nextLine();

                            //Prompts user to enter date of mood to delete
                            System.out.println("Enter a date in the format dd/MM/yyyy");
                            String deleteDateStr = in.nextLine();
                            LocalDate deleteDate = LocalDate.parse(deleteDateStr, dateFormat);

                            //Prompt user to enter time of mood to delete
                            System.out.println("Enter a time in the format HH:mm:ss");
                            String deleteTimeStr = in.nextLine();
                            LocalTime deleteTime = LocalTime.parse(deleteTimeStr, timeFormat);

                            deleteByName(deleteName.toUpperCase(), deleteDate.toString(), deleteTime.toString(), moodList);
                        } else {
                            System.out.println("Invalid Choice");
                        }
                    } catch (DateTimeParseException dpe){
                        System.out.println("Error ocurred in date or time parsing: " + dpe.getMessage());
                    }

                    break;

                    //Edit Mood Notes
                case "e":
                    //Prompts user to enter name of mood to edit
                    System.out.println("Enter a name");
                    String editName = in.nextLine();

                    try {
                        //Prompts user to enter date of mood to edit
                        System.out.println("Enter a date in the format dd/MM/yyyy");
                        String editDateStr = in.nextLine();
                        LocalDate editDate = LocalDate.parse(editDateStr, dateFormat);

                        //Prompt user to enter time of mood to edit
                        System.out.println("Enter a time in the format HH:mm:ss");
                        String editTimeStr = in.nextLine();
                        LocalTime editTime = LocalTime.parse(editTimeStr, timeFormat);

                        editMoodNotes(editName.toUpperCase(), editDate.toString(), editTime.toString(), moodList);
                    } catch (DateTimeParseException dpe){
                        System.out.println("Error ocurred in date or time parsing: " + dpe.getMessage());
                    }

                    break;

                    //Search
                case "s":
                    System.out.println("\nEnter 1 to search by date " +
                            "\nEnter 2 to search by name, date and time");
                    String searchChoice = in.nextLine();
                    try {
                        if (searchChoice.equals("1")) {//Searchs by date
                            System.out.println("Enter a date in the format dd/MM/yyyy");
                            String searchDateStr = in.nextLine();
                            LocalDate searchDate = LocalDate.parse(searchDateStr, dateFormat);

                            searchByDate(searchDate.toString(), moodList);
                        } else if (searchChoice.equals("2")) {//Searchs by name, date and time
                            //Prompts user to enter name of mood to search
                            System.out.println("Enter a name");
                            String searchName = in.nextLine();

                            //Prompts user to enter date of mood to search
                            System.out.println("Enter a date in the format dd/MM/yyyy");
                            String searchDateStr = in.nextLine();
                            LocalDate searchDate = LocalDate.parse(searchDateStr, dateFormat);

                            //Prompt user to enter time of mood to search
                            System.out.println("Enter a time in the format HH:mm:ss");
                            String searchTimeStr = in.nextLine();
                            LocalTime searchTime = LocalTime.parse(searchTimeStr, timeFormat);

                            searchByAll(searchName.toUpperCase(), searchDate.toString(), searchTime.toString(), moodList);
                        } else {
                            System.out.println("Invalid Choice");
                        }
                    } catch (DateTimeParseException dpe){
                        System.out.println("Error ocurred in date or time parsing: " + dpe.getMessage());
                    }

                    break;

                case "M":
                    System.out.println("All moods stored");
                    for(Mood moodUnit : moodList){
                        System.out.println(moodUnit.toString());
                    }

                    break;

                case "w":
                    File file = new File("moodtracker.txt");
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
                        for(Mood moodUnit : moodList) {
                            writer.write(moodUnit.toString() + "\n");
                        }

                        System.out.print("Writing to file successful");
                    } catch (IOException ioe){
                        ioe.printStackTrace();
                    }
                    break;

                    //Exit menu
                case "Exit":
                    System.out.println("Bye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid Choice");
                    break;
            }

        }
    }
}

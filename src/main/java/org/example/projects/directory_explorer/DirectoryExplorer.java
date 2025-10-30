package org.example.projects.directory_explorer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DirectoryExplorer{
    //Method to handle file management operations like rename and delete
    private static void fileManagement(File file){
        Scanner in = new Scanner(System.in);

        System.out.println("\nPress 1 to rename file " +
                "\nPress 2 to delete file" +
                "\nPress any other key to exit");
        String fileActionChoice = in.nextLine(); //ISer enters their choice

        //File Operations
        try {
            if (fileActionChoice.equals("1")) {
                System.out.println("Enter the new name for the file");
                String fileNewName = in.nextLine();
                //Rename file
                boolean isFileNameChanged = file.renameTo(new File(file.getParent(), fileNewName));

                //Checks if file has beem renamed
                if (isFileNameChanged){
                    System.out.println("File renamed successfully");
                } else {
                    System.out.println("File could not be renamed ");
                }
            } else if (fileActionChoice.equals("2")) {
                //Deletes the file
                boolean isDeleted = file.delete();

                //Checks if the file has been deleted
                if (isDeleted){
                    System.out.println("File deleted successfully");
                } else {
                    System.out.println("File could not be deleted");
                }
                System.out.println("File deleted successfully");
            }
        } catch (NullPointerException npe){
            System.err.println("Error performing file operations: " + npe.getMessage());
        }
    }

    //Directory Operations
    private static void directoryManagement(File file){
        Scanner in = new Scanner(System.in);
        List<String> directoryList = new ArrayList<>();
        System.out.println("Press 1 to list files in directory" +
                "\nPress 2 to rename directory" +
                "\nPress 3 to delete directory " +
                "\nPress any other key to exit");
        String userChoice = in.nextLine();
        directoryList = List.of(file.list());

        try {
            //Printout list of files in the directory
            if (userChoice.equals("1")) {
                for (String s : directoryList) {
                    System.out.println(s);
                }
            } else if(userChoice.equals("2")){
                //Rename directory
                System.out.println("Enter new directory name");
                String directoryNewName = in.nextLine();

                boolean isDirNameChanged = file.renameTo(new File(file.getParent(), directoryNewName));

                //Checks if file has been renamed
                if (isDirNameChanged){
                    System.out.println("Directory renamed successfully");
                } else {
                    System.out.println("Directory could not be renamed ");
                }
            } else if (userChoice.equals("3")){
                boolean isDirDeleted = file.delete();

                if(isDirDeleted){
                    System.out.println("Directory deleted successfully");
                } else {
                    System.out.println("Directory could not be deleted");
                }
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e){
            System.err.println("Error in directory list: " + e.getMessage());
        }
    }


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String fileName;

        while(true){
            System.out.println("\nPress 1 for File Management " +
                    "\nAny other key to exit");
            //User enters their choice
            String userChoice = in.nextLine();

            //File Management
            if (userChoice.equals("1")){
                //
                System.out.println("Enter the name of the file or directory with the path");
                fileName = in.nextLine();

                //Create file object
                File file = new File(fileName);


                if(file.exists()){ //Check if the file exists

                    //Check if the file object is a file or a directory
                    if (file.isFile()){
                        System.out.println(fileName + " is a file");
                        fileManagement(file);
                    } else {
                        System.out.println(fileName + " is a directory");
                    }
                } else {
                    //Create the file or directory if it doesn't exist
                    System.out.println(fileName + " is not a valid file or directory");
                    System.out.println("To create a file with given name press 1 \n" +
                            "To create a directory woth given name press 2 \n" +
                            "To do nothing and continue, press any other key");
                    //User enters choice
                    String createChoice = in.nextLine();

                    //Create the file
                    if (createChoice.equals("1")){
                        //Get the parent directory
                        String parentDirStr = file.getParent();
                        File parentDir = new File(parentDirStr); //parent directory File object

                        //Create parent directory if it doesn't exist
                        if(!parentDir.exists()){
                            boolean created = parentDir.mkdirs();
                            if(!created){ //Checks if parent directory can be created
                                System.out.println("The parent directory of " + fileName + "could not be created");
                                continue;
                            }
                        }
                        try{
                            file.createNewFile();
                        } catch (IOException ioe){
                            System.err.println("Unable to create file: " + ioe.getMessage());
                        }
                    }
                    //Create the directory
                    else if(createChoice.equals("2")){
                        //Create the directory
                        boolean created = file.mkdirs();
                        if(created){
                            System.out.println("The directory " + fileName + "has been created");
                        } else {
                            System.out.println("The directory has not been created");
                        }
                    }
                }
                //Exit
            } else {
                System.out.println("Bye");
                break;
            }
        }
    }
}


package org.example.projects.pet_care_scheduler;

import java.io.*;
import java.util.*;

public class PetCareScheduler {
    private static final Scanner scan = new Scanner(System.in);
    private static final PetService petService = new PetService();
    private static final Map<String, Pet> pets = petService.getAllPets();
    private static final List<Appointment> appointments = petService.getAllAppointments();

    public static void main(String[] args) {
        File file = new File("/data/petCareData.ser");

        if(file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    Object obj = in.readObject();
                    if (obj instanceof Pet) {
                        Pet pet = (Pet) obj;
                        pets.put(pet.getId(), pet);
                    } else if (obj instanceof Appointment) {
                        Appointment appointment = (Appointment) obj;
                        appointments.add(appointment);
                    }

                }
            } catch (EOFException e){
                System.out.println("Pets and appointments loaded");
            } catch (ClassNotFoundException | IOException e) {
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }

        boolean running = true;
        while(running){
            System.out.println("\n     WELCOME TO THE PET SCHEDULER     ");
            System.out.println("=======================================================================");
            System.out.println("Enter a number to perform an operation from the following");
            System.out.println("1. Register Pets");
            System.out.println("2. Schedule appointments");
            System.out.println("3. Store data");
            System.out.println("4. Display records");
            System.out.println("5. Generate Reports");
            System.out.println("6. Exit");

            String userChoice = scan.nextLine().strip();

            switch(userChoice){
                case "1":
                    petService.registerPet();
                    break;
                case "2":
                    petService.scheduleAppointment();
                    break;
                case "3":
                    petService.storeData();
                    break;
                case "4":
                    petService.displayDetails();
                    break;
                case "5":
                    petService.generateReport();
                    break;
                case "6":
                    System.out.println("Bye");
                    running = false;
                    break;
                default :
                    System.out.println("Invalid choice");
                    break;
            }
        }

    }
}

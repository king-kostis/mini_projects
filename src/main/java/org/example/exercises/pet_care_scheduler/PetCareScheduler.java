package org.example.exercises.pet_care_scheduler;

import java.io.*;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class PetCareScheduler {
    private static final Scanner scan = new Scanner(System.in);
    private static final Map<String, Pet> pets = new HashMap<>();
    private static final List<Appointment> appointments = new ArrayList<>();
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    //register pet
    private static void registerPet(){
        System.out.print("Enter pet id: ");
        String petId = scan.nextLine().strip();

        System.out.print("Enter pet name: ");
        String petName = scan.nextLine().strip().toUpperCase();

        System.out.print("Enter pet breed: ");
        String petBreed = scan.nextLine().strip().toUpperCase();

        System.out.print("Enter pet age: ");
        int petAge = Integer.parseInt(scan.nextLine().strip()) ;

        System.out.print("Enter pet ownerName: ");
        String petOwnerName = scan.nextLine().strip();

        System.out.print("Enter your contactInfo: ");
        String contactInfo = scan.nextLine().strip();

        //Checks if pet object already exists
        if(pets.containsKey(petId)){
            System.out.println("Error registering pet: Pet already exists");
            return;
        }

        //Create pet object
        Pet pet = new Pet(petId, petName, petBreed, petAge, petOwnerName, contactInfo);

        //add pet to pets list
        pets.put(petId, pet);
        System.out.println("Pet registered successfully");
    }

    //adds appointment for a pet to appointment list
    private static void scheduleAppointment(){
        System.out.println("Enter pet id: ");
        String petId = scan.nextLine().strip();

        if(pets.isEmpty()){
            System.out.println("There are no pets registered");
            return;
        }
        //Checks if pet exists in HashMap
        else if(!(pets.containsKey(petId))){
            System.out.println("Error: Pet does not exist");
            return;
        }

        //brings chosen pet out of list
        Pet pet = pets.get(petId);

        //checks if appointment type is valid and iterates the prompt till its right
        boolean isAppointmentInvalid = true;
        String appointmentType = null;
        while(isAppointmentInvalid){
            try {
                System.out.println("Enter appoitnment type (visit, vaccination, grooming): ");
                appointmentType = scan.nextLine().strip();

                //checks if appointment type is valid
                if(!(appointmentType.equals("visit") || appointmentType.equals("vaccination") || appointmentType.equals("grooming")) ){
                    System.out.println("Invalid appointment type. Enter correct appointment type");
                }else {
                    isAppointmentInvalid = false;
                }
            } catch (NullPointerException e){
                System.err.println("Error" + e.getMessage());
            }
        }

        //checks if date and time entered is valid and iterates prompt till its right
        LocalDateTime dateTime = null;
        boolean isDateTimeInvalid = true;
        while(isDateTimeInvalid){
            System.out.println("Enter appointment date in format (dd/MM/yyyy HH:mm:ss): ");
            String dateTimeStr = scan.nextLine().strip();

            try {
                dateTime = LocalDateTime.parse(dateTimeStr, dateTimeFormat);

                //checks if date and time is valid
                if(dateTime.isEqual(LocalDateTime.now()) || dateTime.isBefore(LocalDateTime.now())){
                    System.out.println("Invalid date input. Try again");
                } else {
                    isDateTimeInvalid = false;
                }
            } catch (DateTimeParseException e){
                System.err.println("Error parsing date and time: " + e.getMessage());
            } catch (NullPointerException e){
                System.err.println("Error: " + e.getMessage());
            }
        }

        //creates appointment object
        Appointment appointment = new Appointment(appointmentType, dateTime);

        //adds appointment to pet appointment list
        pet.getAppointments().add(appointment);

        //adds appointment to pet list
        appointments.add(appointment);
        System.out.println("Appointment added successfully");
    }

    //stores pet and appointment objects in a file
    private static void storeData(){
        File file = new File("petCareData.ser");//stores file as object

        if(pets.isEmpty()){
            System.out.println("There are no pets registered");
            return;
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
            //prints out pet objects to file
            for(String id : pets.keySet()){
                out.writeObject(pets.get(id));
            }

            //prints out appointment objects to file
            for(int i = 0; i < appointments.size(); i++){
                out.writeObject(appointments.get(i));
            }

            System.out.println("Data Stored succesfully");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            System.err.println("Error in storing data: " + e.getMessage());
        }
    }

    //displays details to user
    private static void displayDetails(){
        System.out.println("Choose which details to display");
        System.out.println("1. All registered pets");
        System.out.println("2. All appointments for a specific pet");
        System.out.println("3. Upcoming appointments for all pets");
        System.out.println("4. Past appointments for each pets");
        String userChoice = scan.nextLine().strip();

        if(pets.isEmpty()){
            System.out.println("There are no pets registered");
            return;
        }

        if(userChoice.equals("1")){
            //iterates map and prints out information of every pet
            for(String id : pets.keySet()){
                System.out.println(pets.get(id) + "\n");
            }
        } else if(userChoice.equals("2")){
            //prints all appointments for each pet
            for(String id : pets.keySet()){
                pets.get(id)// gets pet object
                        .getAppointments()// gets appointment list of pet object
                        .forEach(appoitnment -> System.out.println(pets.get(id).getName() + " " + appoitnment + "\n")); //prints out every appointment from pet object
            }
        } else if(userChoice.equals("3")){
            //prints all upcoming appointments for all pets
            for(int i = 0; i < appointments.size(); i++){
                //checks if is after current date before printing
                if(appointments.get(i).getDateTime().isAfter(LocalDateTime.now())){
                    System.out.println(appointments.get(i) + "\n");
                }
            }
        } else if(userChoice.equals("4")){
            //prints out past appointments for each pet
            for(String id : pets.keySet()){
                pets.get(id)//gets pet object
                        .getAppointments()//gets appointment list of pet object
                        .stream()//converts list to stream
                        .filter(appoitnment -> appoitnment.getDateTime().isBefore(LocalDateTime.now()))//filters appointment dates to only previous dates from today
                        .forEach(appoitnment -> System.out.println(pets.get(id).getName() + " " + appoitnment + "\n"));//prints out the pets and past appointments
            }
        } else {
            System.out.println("Invalid Choice");
        }
    }

    //generate report
    private static void generateReport(){
        System.out.println("\nUPCOMING APPOINTMENTS");
        System.out.println("=====================================================");
        //prints pets with upcoming appointments, at least a week away
        for(String id : pets.keySet()){
            for (int i = 0; i < pets.get(id).getAppointments().size(); i++){
                //checks if appointment date is at least a week away before printing
                if((pets.get(id).getAppointments().get(i).getDateTime().isAfter(LocalDateTime.now())) && (Period.between(LocalDateTime.now().toLocalDate(), pets.get(id).getAppointments().get(i).getDateTime().toLocalDate()).getDays() >= 7)){
                    System.out.println(pets.get(id) + pets.get(id).getAppointments().get(i).getDateTime().toString() + "\n");
                }
            }
        }

        System.out.println("\nOVERDUE VET VISITS");
        System.out.println("=====================================================");
        for(String id : pets.keySet()){
            for (int i = 0; i < pets.get(id).getAppointments().size(); i++){
                //checks if appointment date is at least a week away before printing
                if((pets.get(id).getAppointments().get(i).getDateTime().isBefore(LocalDateTime.now())) && (Period.between(LocalDateTime.now().toLocalDate(), pets.get(id).getAppointments().get(i).getDateTime().toLocalDate()).getMonths() >= 6)){
                    System.out.println(pets.get(id) + pets.get(id).getAppointments().get(i).getDateTime().toString() + "\n");
                }
            }
        }
    }


    public static void main(String[] args) {
        File file = new File("petCareData.ser");
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
            while(true){
                Object obj = in.readObject();
                if(obj instanceof Pet){
                    Pet pet = (Pet) obj;
                    pets.put(pet.getId(), pet);
                } else if(obj instanceof Appointment){
                    Appointment appointment = (Appointment) obj;
                    appointments.add(appointment);
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (EOFException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e){
            System.err.println("Error: " + e.getMessage());
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
                    registerPet();
                    break;
                case "2":
                    scheduleAppointment();
                    break;
                case "3":
                    storeData();
                    break;
                case "4":
                    displayDetails();
                    break;
                case "5":
                    generateReport();
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

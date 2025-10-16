package org.example.exercises.pet_care_scheduler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pet {
    private String id;
    private String name;
    private String breed;
    private int age;
    private String ownerName;
    private String contactInfo;
    private LocalDate registerDate;
    private List<Appointment> appointments;

    public Pet(String id, String name, String breed, int age, String ownerName, String contactInfo){
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.ownerName = ownerName;
        this.contactInfo = contactInfo;
        this.registerDate = LocalDate.now();
        this.appointments = new ArrayList<>();
    }

    public String getId(){ return this.id; }
    public String getName(){ return this.name; }
    public String getBreed(){ return this.breed; }
    public int getAge(){ return this.age; }
    public String getOwnerName(){ return this.ownerName; }
    public String getContactInfo(){ return this.contactInfo; }
    public LocalDate getRegisterDate(){ return this.registerDate; }
    public List<Appointment> getAppointments(){ return this.appointments; }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setBreed(String breed){
        this.breed = breed;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setOwnerName(String ownerName){
        this.ownerName = ownerName;
    }

    public void setContactInfo(String contactInfo){
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString(){
        return "\nName: " +this.name +
                "\nBreed: " + this.breed +
                "\nAge: " + this.age +
                "\nOwner Name: " + this.ownerName +
                "\nContact Info: " + this.contactInfo;
    }
}

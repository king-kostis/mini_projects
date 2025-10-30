package org.example.projects.pet_care_scheduler;

import java.time.LocalDateTime;

public class Appointment {
    private String appointmentType;
    private LocalDateTime dateTime;
    private String notes;

    public Appointment(String appointmentType, LocalDateTime dateTime) {
        this.appointmentType = appointmentType;
        this.dateTime = dateTime;
    }

    public Appointment(){
        this.notes = null;
    }

    public String getAppointmentType(){
        return this.appointmentType;
    }

    public LocalDateTime getDateTime(){
        return this.dateTime;
    }

    public String getNotes(){
        return this.notes;
    }

    public void setAppointmentType(String appointmentType){
        this.appointmentType = appointmentType;
    }

    public void setDateTime(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    @Override
    public String toString(){
        return "\nAppointment Type: " + this.appointmentType +
                "\nAppointment Date: " + this.dateTime +
                "\nNotes: " + this.notes;
    }
}

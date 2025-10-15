package org.example.mini_projects.mood_tracker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;


public class Mood {
    private String name;
    private LocalDate date;
    private LocalTime time;
    private String notes;

    public Mood(){
        this.name = null;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.notes = null;
    }

    public Mood(String name){
        this.name = name;
    }

    public Mood(String name, LocalDate date){
        this.name = name;
        this.date = LocalDate.now();
    }

    public Mood(String name, LocalDate date, LocalTime time){
        this.name = name;
        this.date = LocalDate.now();
        this.time = LocalTime.MIDNIGHT;
    }

    public Mood(String name, String notes){
        this.name = name;
        this.notes = notes;
    }

    public Mood(String name, LocalDate date, String notes){
        this.name = name;
        this.date = LocalDate.now();
        this.notes = notes;
    }

    public Mood(String name, LocalDate date, LocalTime time, String notes){
        this.name = name;
        this.date = LocalDate.now();
        this.time = LocalTime.MIDNIGHT;
        this.notes = notes;
    }

    public String getName(){
        return this.name;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public LocalTime getTime(){
        return this.time;
    }

    public String getNotes(){
        return this.notes;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setTime(LocalTime time){
        this.time = time;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    @Override
    public String toString(){
        return "\nName: " + this.name +
                "\nDate: " + this.date +
                "\nTime: " + this.time +
                "\nNotes: " + this.notes;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Mood other = (Mood) obj;
        return Objects.equals(name, other.name);
    }
}

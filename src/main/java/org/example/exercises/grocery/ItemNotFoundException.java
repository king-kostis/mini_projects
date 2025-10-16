package org.example.exercises.grocery;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String message){
        super(message);
    }
}
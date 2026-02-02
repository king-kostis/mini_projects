package org.example.projects.array;

import java.util.Arrays;

public class ArrayImpl {
   static int[] append(int[] arr, int num){
        int end = arr.length - 1;
        int[] newArr = new int[arr.length + 1];

        //Move old array contents to new array
        for(int i = 0; i <= end; i++){
            newArr[i] = arr[i];
        }
        newArr[end + 1] = num; // append number to last index of new array
        return newArr;

   }

   static int[] insert(int[] arr, int index, int num){
       int[] newArr = new int[arr.length + 1];

       for(int i = 0; i <= arr.length-1; i++){
           // Shifts elements after the insertion index
           if(i >= index){
               newArr[i+1] = arr[i];
           }
           else {
               newArr[i] = arr[i];
           }
       }

       newArr[index] = num;
       return newArr;
   }

   static int[] pop(int[] arr){
       int end, newLength;
       end = newLength = arr.length-1;
       int[] newArr = new int[newLength];

       for(int i = 0; i < end; i++){
           newArr[i] = arr[i];
       }
       return newArr;
   }



   public static void main(String[] args){
       int[] arr = {1,2,3,4,5};

       System.out.println(Arrays.toString(append(arr, 23)));
       System.out.println(Arrays.toString(insert(arr, 4, 54)));
       System.out.println(Arrays.toString(pop(arr)));
   }

}

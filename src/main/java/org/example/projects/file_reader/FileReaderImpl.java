package org.example.projects.file_reader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.Buffer;

public class FileReaderImpl {
    public static void main(String[] args){
        int count = 0;
        String s = "";
        try ( BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\HP\\Documents\\Projects\\learn2\\log.txt"));
              BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\HP\\Documents\\Projects\\learn2\\log-output.txt"))
        )
        {
            while((s = br.readLine()) != null){
                if(s.contains("INFO") || s.contains("WARNING") || s.contains("ERROR")){
                    count++;
                }
                bw.write(s + "\n");
                System.out.println(s + "\n");
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("Number of logs: " + count);
    }
}

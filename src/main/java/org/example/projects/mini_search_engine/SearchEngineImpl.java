package org.example.projects.mini_search_engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SearchEngineImpl {
    static void read(File file)throws IOException {
        Map<String, Integer> words = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s = "";
        while ((s = br.readLine()) != null){
            for(int i = 0; i <= s.length(); i++){
                if(s.charAt(i) == ' '){

                }
            }
        }

    }
}

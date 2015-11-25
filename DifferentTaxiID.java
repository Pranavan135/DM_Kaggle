
import com.csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class DifferentTaxiID {
     public static void main(String[] args) throws FileNotFoundException, IOException{
        long count = 0;
        HashMap<String, Integer> map = new HashMap<>();
        PrintWriter pw = new PrintWriter("taxiId.txt");
        CsvReader cs[] = new CsvReader[17];
        for(int i = 0; i < 17; i++){
            cs[i] = new CsvReader(i+".csv");
            
            while(cs[i].readRecord()){
                    String stand = cs[i].get(4);
                    if(map.get(stand) == null){
                        map.put(stand,1);
                        count++;
                    }
                    else{
                        int v = map.get(stand);
                        map.put(stand, v+1);
                        count++;
                    }
                
            }
        }
        
        for(String s : map.keySet()){
            System.out.println(s+" " + map.get(s));
            pw.println(s+" " + map.get(s));
        }
        pw.close();
        System.out.println(count);
    }
}

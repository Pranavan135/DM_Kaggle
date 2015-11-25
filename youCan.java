
import com.csvreader.CsvReader;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
public class youCan {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("train.csv"));
        PrintWriter[] pw = new PrintWriter[34];

        System.out.println(scanner.nextLine());
        for (int j = 0; j < 34 && scanner.hasNextLine(); j++) {
        
            pw[j] = new PrintWriter(j+".csv");
            for (int i = 0; i < 50250 && scanner.hasNextLine(); ) {
                String sentence = scanner.nextLine();
                
                if(!sentence.trim().contains("[]")) {
                    pw[j].println(sentence);
                    i++;
                }
            }
            pw[j].close();
        }
    }
}

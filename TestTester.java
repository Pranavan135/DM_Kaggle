import com.csvreader.CsvReader;

import java.io.IOException;

/**
 * Created by pranavan on 7/1/15.
 */
public class TestTester {
    public static void main(String[] args) throws IOException{
        CsvReader csvReader = new CsvReader("train.csv");

        csvReader.readHeaders();
        int count = 0;

        while(csvReader.readRecord()){
            String callID = csvReader.get(2);

            if(!(callID.equalsIgnoreCase("NA") || callID.equalsIgnoreCase(""))){
                count++;
                System.out.println(callID);
            }
        }

        System.out.println(count);
    }
}

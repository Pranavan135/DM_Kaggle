import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.FileWriter;
import java.io.IOException;


/**
 * Created by pranavan on 27/06/15.
 */
public class Tester002 {
    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();
        CsvReader data = new CsvReader("train.csv");
        CsvWriter a = new CsvWriter(new FileWriter("a.csv"), ',');
        CsvWriter b = new CsvWriter(new FileWriter("b.csv"), ',');
        CsvWriter c = new CsvWriter(new FileWriter("c.csv"), ',');



        int aCount = 0;
        int bCount = 0;
        int cCount = 0;


        while (data.readRecord()) {


            if (data.get(8).split(",").length / 2 - 1 >= 0) {
                if (data.get(1).equalsIgnoreCase("A")) {
                    a.writeRecord(data.getValues());
                    a.endRecord();
                    ++aCount;
                } else if (data.get(1).equalsIgnoreCase("B")) {
                    b.writeRecord(data.getValues());
                    a.endRecord();
                    ++bCount;
                } else if (data.get(1).equalsIgnoreCase("C")) {
                    c.writeRecord(data.getValues());
                    a.endRecord();
                    ++cCount;
                }
            }

        }

        a.close();
        b.close();
        c.close();

        long endTime = System.currentTimeMillis();

        System.out.println(aCount);
        System.out.println(bCount);
        System.out.println(cCount);
        System.out.println(aCount + bCount + cCount);
        System.out.println((endTime - startTime) / 1000 + " seconds");

    }
}

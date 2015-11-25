import com.csvreader.CsvReader;
import org.apache.commons.math3.stat.StatUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by pranavan on 27/06/15.
 */
public class Tester_001 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        int noOfRecords = 1674160;
        double[] noOfPolyLines = new double[noOfRecords];

        long startTime = System.currentTimeMillis();
        CsvReader data = new CsvReader("test.csv");
        data.readHeaders();

        int count = 0;

        while (data.readRecord()) {
            String[] parts = data.get(8).split(",");
            noOfPolyLines[count] = parts.length / 2 - 1;
            if (noOfPolyLines[count] > 0) {
                System.out.println("T" + count + " " + noOfPolyLines[count] * 15.00000);
                ++count;
            } else {
                System.out.println("OOPS");
            }

        }

        long endTime = System.currentTimeMillis();

        //System.out.println(count);
        System.out.println(Math.sqrt(StatUtils.populationVariance(noOfPolyLines)) * 15.000000);
        System.out.println((endTime - startTime) / 1000 + " seconds");
    }
}

import com.csvreader.CsvReader;
import org.apache.commons.math3.stat.StatUtils;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by pranavan on 26/06/15.
 */
public class Tester {
    public static void main(String[] args) throws IOException, FileNotFoundException {
//        List<Pair> positions = new ArrayList<Pair>();
        int noOfRecords = 1704769;
        double[] noOfPolyLines = new double[noOfRecords];
        double mean = 0.0;
        int xCount = 0;

        long startTime = System.currentTimeMillis();
        CsvReader data = new CsvReader("train.csv");
        data.readHeaders();

        int count = 0;

        while (data.readRecord()) {
            String[] parts = data.get(8).split(",");
            noOfPolyLines[count] = parts.length / 2 - 1;
            if (noOfPolyLines[count] >= 0) {
                mean = mean + noOfPolyLines[count] / noOfRecords;
                ++count;
            } else {
                System.out.println(data.get(8) + " " + noOfPolyLines[count]);
                xCount++;
            }

        }

        long endTime = System.currentTimeMillis();

        System.out.println(xCount);
        System.out.println(count);
        System.out.println(mean * 15.0000000);
        System.out.println(StatUtils.mean(noOfPolyLines)*15.0000000);
        System.out.println(Math.sqrt(StatUtils.populationVariance(noOfPolyLines)) * 15.000000);
        System.out.println((endTime - startTime) / 1000 + " seconds");
    }

}


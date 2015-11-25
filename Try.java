import com.csvreader.CsvReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by pranavan on 30/06/15.
 */
public class Try {
    public static void main(String[] args) throws IOException {
        CsvReader csvReader1 = new CsvReader("test.csv");
        CsvReader csvReader2 = new CsvReader("motivated.csv");

        HashMap<String, Double> countMap = getNumberOfTripsPerTaxi();
        HashMap<String, Double> meanMap = getMeanTaxiTime();
        double[] times = new double[320];
        int index = 0;
        double constant = 20.0;

        csvReader1.readHeaders();
        csvReader2.readHeaders();

        while (csvReader1.readRecord() && csvReader2.readRecord()) {

            double earlVal = Double.parseDouble(csvReader2.get(1));

            String taxiID = csvReader1.get(4);

            double newVal = meanMap.get(taxiID);

            double count = countMap.get(taxiID);

            times[index] = earlVal + constant * newVal * count / 1704769;

            index++;
        }


        for (int i = 0; i < 320; i++) {
            System.out.println(times[i]);
        }
    }


    private static HashMap<String, Double> getNumberOfTripsPerTaxi() throws IOException {
        Scanner scanner = new Scanner(new File("taxiMean.txt"));
        HashMap<String, Double> countMap = new HashMap<>();

        while (scanner.hasNext()) {

            countMap.put(scanner.next(), scanner.nextDouble());
            scanner.nextDouble();
        }

        return countMap;
    }

    private static HashMap<String, Double> getMeanTaxiTime() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("taxiMean.txt"));
        HashMap<String, Double> meanMap = new HashMap<>();

        while (scanner.hasNext()) {
            String s = scanner.next();
            scanner.nextDouble();
            double d = scanner.nextDouble();
            meanMap.put(s, d);

        }

        return meanMap;
    }
}
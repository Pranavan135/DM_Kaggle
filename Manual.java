import com.csvreader.CsvReader;

import java.io.IOException;

/**
 * Created by pranavan on 7/1/15.
 */
public class Manual {
    public static void main(String[] args) throws IOException {

        CsvReader csvReader = new CsvReader("test.csv");


        while (csvReader.readRecord()) {
            String callID = csvReader.get(2);
            String part[] = csvReader.get(8).replaceAll("\\[", "").replaceAll("]", "").split(",");
            double dis = (part.length/2 - 1) * 15.0000;
            if (!(callID.equalsIgnoreCase("NA") || callID.equalsIgnoreCase(""))) {
                CsvReader csvReader2 = new CsvReader("train.csv");
                csvReader2.readHeaders();
                int count = 0;
                double sum = 0.0;
                while (csvReader2.readRecord()) {

                    if (csvReader2.get(2).equalsIgnoreCase(callID)) {

                        String partsa[] = csvReader2.get(8).replaceAll("\\[", "").replaceAll("]", "").split(",");

                        if (partsa.length >= part.length) {
                            sum = sum + (partsa.length / 2 - 1);
                            count++;
                        }
                    }
                }
                if(count >= 1)
                    System.out.println(csvReader.get(0) + " " + csvReader.get(2) + " " + count + " " + sum * 15.000 / count + " " + dis);
            }
        }

    }
}

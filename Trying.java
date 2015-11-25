import com.csvreader.CsvReader;

import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author DELL
 */
public class Trying {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        CsvReader[] cs = new CsvReader[34];
        double[] time = new double[320];
        int value = 34;

        for (int i = 0; i < value; i++) {
            cs[i] = new CsvReader(i + ".csv");
        }
        CsvReader cs1 = new CsvReader("test.csv");
        cs1.readHeaders();
        int count = 0;
        while (cs1.readRecord()) {
            double minTime = 0;
            String type = cs1.get(1).trim();


            String line = cs1.get(8).replaceAll("\\[", "").replaceAll("]", "");
            String[] parts = line.split(",");
            double part[] = new double[parts.length];
            for (int i = 0; i < parts.length; i++) {
                part[i] = Double.parseDouble(parts[i]);
            }
            double[] mins = new double[value];

            double[] minsTime = new double[value];

            for (int i = 0; i < value; i++) {
                mins[i] = Double.MAX_VALUE;

                minsTime[i] = 0.0;


                cs[i] = new CsvReader(i + ".csv");
                while (cs[i].readRecord()) {

                    String partsa[] = cs[i].get(8).replaceAll("\\[", "").replaceAll("]", "").split(",");
                    double parta[] = new double[partsa.length];
                    for (int j = 0; j < partsa.length; j++) {
                        parta[j] = Double.parseDouble(partsa[j]);
                    }
                    if (type.equalsIgnoreCase(cs[i].get(1).trim())) {

                        if (parta.length >= part.length) {
                            double c = 0;
                            for (int k = 0; k < part.length; k += 2) {
                                c = c + VincentyDistanceCalculator.getDistance(parta[k + 1], parta[k], part[k + 1], part[k]);
                                if (c > mins[i]) {
                                    break;
                                }

                            }
                            if (c < mins[i]) {
                                mins[i] = c;
                                minsTime[i] = (parta.length / 2 - 1) * 15.000;
                            }
                        }

                    }
                }


                minTime = minTime + minsTime[i] / (double) value;


            }

            time[count] = minTime;
            System.out.println(cs1.get(0) + "," + minTime);
            count++;

        }

        for (double d : time) {
            System.out.println(d);
        }

    }
}

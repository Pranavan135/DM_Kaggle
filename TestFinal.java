
import com.csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
public class TestFinal {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        CsvReader[] cs = new CsvReader[18];
        double[] time = new double[320];
        int value = 17;

        for (int i = 0; i < value; i++) {
            cs[i] = new CsvReader(i + ".csv");
        }
        CsvReader cs1 = new CsvReader("test.csv");
        cs1.readHeaders();
        int count = 0;
        while (cs1.readRecord()) {
            double min = Double.MAX_VALUE;
            double minTime = 0;
            String type = cs1.get(1).trim();
            String tip = cs1.get(3).trim();
            String call = cs1.get(2).trim();

            //  System.out.println(cs1.get(8));
            String line = cs1.get(8).replaceAll("\\[", "").replaceAll("]", "");
            String[] parts = line.split(",");
            double part[] = new double[parts.length];
            for (int i = 0; i < parts.length; i++) {
                part[i] = Double.parseDouble(parts[i]);
            }
            double[] mins = new double[value];
            double[] taxiIdmins = new double[value];
            double[] minsTime = new double[value];
            double[] taxiIdminsTime = new double[value];

            for (int i = 0; i < value; i++) {
                mins[i] = Double.MAX_VALUE;
                taxiIdmins[i] = Double.MAX_VALUE;
                minsTime[i] = 0.0;
                taxiIdminsTime[i] = 0.0;
                boolean x = false;
                boolean y = false;

                cs[i] = new CsvReader(i + ".csv");
                while (!x && cs[i].readRecord()) {

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

                        if (type.equals("B")&& tip.equalsIgnoreCase(cs[i].get(3).trim())) {
                            if (parta.length >= part.length) {
                                double c = 0;
                                for (int k = 0; k < part.length; k += 2) {
                                    c = c + VincentyDistanceCalculator.getDistance(parta[k + 1], parta[k], part[k + 1], part[k]);
                                    if (c > taxiIdmins[i]) {
                                        break;
                                    }

                                }
                                if (c < taxiIdmins[i]) {
                                    y = true;
                                    taxiIdmins[i] = c;
                                    taxiIdminsTime[i] = (parta.length / 2 - 1) * 15.000;
                                }
                            }
                        }

                        if (type.equals("A") && call.equalsIgnoreCase(cs[i].get(2).trim())) {
                            if (parta.length >= part.length) {
                                minTime = (parta.length / 2 - 1) * 15.000;
                                x = true;
                            }
                        }

                    }
                }

                if (!x){
                    if(y){
                        minTime = minTime + taxiIdmins[i] / (double) value;
                    }
                    else{
                        minTime = minTime + mins[i] / (double) value;
                    }
                }


            }

            time[count] = minTime;
            System.out.println(count + " " + minTime);
            count++;

        }

        for (double d : time) {
            System.out.println(d);
        }

    }
}
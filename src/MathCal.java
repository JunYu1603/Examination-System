import java.util.ArrayList;
import java.util.Collections;
import java.text.DecimalFormat;


public class MathCal {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    // #==[ Methods ]==#

    // getHighest: Gets the highest score in a data set
    public static int gettopMarks(ArrayList<Integer> intList) {

        int topMarks = intList.get(0);
        int n = intList.size();

        for (int i = 1; i < n; i++) {
            if (intList.get(i) > topMarks) {
                topMarks = intList.get(i);
            }
        }

        return topMarks;
    }

    // getLowest: Gets the lowest score in a data set
    public static int getleastMarks(ArrayList<Integer> intList) {

        int leastMarks = intList.get(0);
        int n = intList.size();

        for (int i = 1; i < n; i++) {
            if (intList.get(i) < leastMarks) {
                leastMarks = intList.get(i);
            }
        }

        return leastMarks;
    }

    // getMean: Gets the average value
    public static int getMean(ArrayList<Integer> intList) {

        int sum = 0;
        for (int i=0; i< intList.size(); i++) {
            sum += intList.get(i);
        }
        return sum / intList.size();

    }

    // getMedian
    public static double getMedian(ArrayList<Double> douList) {
        Collections.sort(douList);

        if (douList.size() % 2 == 1)
            return douList.get((douList.size() + 1) / 2 - 1);
        else {
            double lower = douList.get(douList.size() / 2 - 1);
            double upper = douList.get(douList.size() / 2);

            return (lower + upper) / 2.0;
        }
    }



    // Get Mode: Most repeated value
    public static int getMode(ArrayList<Integer> intList) {
        int mode = intList.get(0);
        int maxCount = 0;
        for (int i = 0; i < intList.size(); i++) {
            int value = intList.get(i);
            int count = 1;
            for (int j = 0; j < intList.size(); j++) {
                if (intList.get(j) == value)
                    count++;
                if (count > maxCount) {
                    mode = value;
                    maxCount = count;
                }
            }
        }
        return mode;
    }

    public static String getStandardDeviation(ArrayList<Double> douList) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = douList.size();

        for(double num : douList) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: douList) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        String sd = df.format(Math.sqrt(standardDeviation/length));

        return sd;
    }

}

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class B {

    // Function to format the result ensuring 15 significant digits after the first non-zero digit
    public static String formatWithSignificantDigits(double value, int significantDigits) {
        // Use BigDecimal to control precision and rounding
        BigDecimal bd = new BigDecimal(value);
        // Set scale to a large number of digits to handle very small numbers with leading zeros
        bd = bd.setScale(significantDigits + 10, RoundingMode.HALF_UP);
        
        // Convert to string and remove unnecessary trailing zeros
        String result = bd.stripTrailingZeros().toPlainString();
        
        // If the number starts with '0.', we need to handle the significant digits carefully
        if (result.startsWith("0.")) {
            int nonZeroIndex = 2;  // Start after "0."
            // Find the first non-zero digit
            while (nonZeroIndex < result.length() && result.charAt(nonZeroIndex) == '0') {
                nonZeroIndex++;
            }
            // Extract the significant digits after the first non-zero digit
            String significantPart = result.substring(0, nonZeroIndex + significantDigits);
            return significantPart;
        } else {
            // For normal numbers, return 15 digits after the decimal point
            return result;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // number of test cases

        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();  // number of lines
            double P = sc.nextDouble();  // success percentage

            // Convert P to a probability between 0 and 1
            double P_fraction = P / 100.0;

            // Calculate the required new probability P'
            double exponent = (double)(N - 1) / N;
            double P_new = Math.pow(P_fraction, exponent) * 100;

            // Calculate the difference
            double difference = P_new - P;

            // Format the difference to ensure 15 significant digits after the first non-zero digit
            String formattedDifference = formatWithSignificantDigits(difference, 15);

            // Print the result for this test case
            System.out.printf("Case #%d: %s%n", t, formattedDifference);
        }

        sc.close();
    }
}

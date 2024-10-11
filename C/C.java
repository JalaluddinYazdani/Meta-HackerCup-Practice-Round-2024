import java.util.*;
import java.io.*;

public class C {

    // Function to compute the greatest common divisor
    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Main function to process input and solve each test case
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] ants = new int[N][2];

            // Read the coordinates of the ants
            for (int i = 0; i < N; i++) {
                String[] line = br.readLine().split(" ");
                ants[i][0] = Integer.parseInt(line[0]);
                ants[i][1] = Integer.parseInt(line[1]);
            }

            // If there are only two ants, they are already collinear
            if (N == 2) {
                System.out.println("Case #" + t + ": 0");
                continue;
            }

            int minToMove = N - 1;  // In the worst case, we would need to move N-1 ants

            // For each ant as the reference point
            for (int i = 0; i < N; i++) {
                Map<String, Integer> slopeMap = new HashMap<>();

                for (int j = 0; j < N; j++) {
                    if (i != j) {
                        long dx = ants[j][0] - ants[i][0];
                        long dy = ants[j][1] - ants[i][1];

                        // Reduce the slope dy/dx to its simplest form
                        long g = gcd(Math.abs(dx), Math.abs(dy));

                        dx /= g;
                        dy /= g;

                        // Ensure the slope is always in a standard form
                        if (dx < 0) {
                            dx = -dx;
                            dy = -dy;
                        } else if (dx == 0) {
                            dy = Math.abs(dy);  // Vertical lines should have positive slope
                        }

                        // Create a unique key for the slope
                        String slopeKey = dy + "/" + dx;
                        slopeMap.put(slopeKey, slopeMap.getOrDefault(slopeKey, 0) + 1);
                    }
                }

                // Find the largest group of collinear ants with respect to ant[i]
                int maxCollinear = 0;
                for (int count : slopeMap.values()) {
                    maxCollinear = Math.max(maxCollinear, count);
                }

                // Calculate the number of ants to move
                int antsToMove = N - (maxCollinear + 1);  // +1 to account for the reference ant itself
                minToMove = Math.min(minToMove, antsToMove);
            }

            // Output the result for this test case
            System.out.println("Case #" + t + ": " + minToMove);
        }
    }
}

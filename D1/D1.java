import java.util.Scanner;

public class D1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // Number of test cases

        for (int t = 1; t <= T; t++) {
            int N = sc.nextInt();  // Number of stones
            int G = sc.nextInt();  // Goal position
            long[] energies = new long[N];
            long[] finalPositions = new long[N];  // To store the final positions of stones

            // Read energies
            for (int i = 0; i < N; i++) {
                energies[i] = sc.nextLong();
            }

            // Simulating the stones' movements with collisions
            long currentPosition = 0;
            for (int i = 0; i < N; i++) {
                long energy = energies[i];

                if (currentPosition >= energy) {
                    // Collision occurs, remaining energy is transferred
                    currentPosition = energy;
                } else {
                    // No collision, the stone moves freely
                    currentPosition = energy;
                }

                finalPositions[i] = currentPosition;  // Store the final position
            }

            // After processing all stones, we find the one closest to the goal
            int closestStoneIndex = -1;
            long minDistance = Long.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                long distance = Math.abs(finalPositions[i] - G);
                if (distance < minDistance || (distance == minDistance && i < closestStoneIndex)) {
                    closestStoneIndex = i;
                    minDistance = distance;
                }
            }

            // Output the result for this test case
            System.out.printf("Case #%d: %d %d%n", t, closestStoneIndex + 1, minDistance);
        }

        sc.close();
    }
}

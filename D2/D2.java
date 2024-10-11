import java.util.*;

public class D2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // Number of test cases
        for (int t = 1; t <= T; t++) {
            int N = scanner.nextInt(); // Number of stones
            int G = scanner.nextInt(); // Goal
            int[] energies = new int[N];
            for (int i = 0; i < N; i++) {
                energies[i] = scanner.nextInt();
            }

            // List to store the final positions of the stones
            int[] finalPositions = new int[N];
            Set<Integer> occupied = new HashSet<>(); // Use a set to track occupied positions

            // Simulating the stone throws
            for (int i = 0; i < N; i++) {
                int energy = energies[i];
                int position = 0; // Starting at position 0

                // Move the stone until its energy is exhausted or it collides with another
                while (energy > 0) {
                    if (occupied.contains(position + 1)) {
                        // If the next position is occupied, break to simulate collision
                        break;
                    } else {
                        // Move the stone one unit to the right
                        position++;
                        energy--;
                    }
                }

                // The stone is now stationary at position
                finalPositions[i] = position;
                occupied.add(position); // Mark this position as occupied
            }

            // Find the stone closest to the goal G
            Integer closestStone = null;
            int minDistance = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                int distance = Math.abs(finalPositions[i] - G);
                if (distance < minDistance || (distance == minDistance && (closestStone == null || i < closestStone))) {
                    minDistance = distance;
                    closestStone = i; // Store index of closest stone
                }
            }

            System.out.printf("Case #%d: %d %d%n", t, closestStone + 1, minDistance); // Convert to 1-based index
        }
        scanner.close();
    }
}


import java.util.*;
import java.io.*;

class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // Read the number of test cases
        for (int caseNum = 1; caseNum <= t; caseNum++) {
            System.out.print("Case #" + caseNum + ": ");
            solve(scanner);
        }
        scanner.close();
    }

    public static void solve(Scanner scanner) {
        long n = scanner.nextLong();
        long k = scanner.nextLong();
        long[] time = new long[(int) n];
        for (int i = 0; i < n; i++) {
            time[i] = scanner.nextLong();
        }
        Arrays.sort(time);
        long mini = 2 * time[0] * (n - 1);
        mini -= time[0];
        if (n == 1 || n == 2) mini = time[0];
        if (mini <= k) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static List<Long> sieveOfEratosthenes(int n) {
        boolean[] prime = new boolean[n + 1];
        List<Long> res = new ArrayList<>();
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= n; i += p) prime[i] = false;
            }
        }
        for (int p = 2; p <= n; p++) {
            if (prime[p]) res.add((long) p);
        }
        return res;
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

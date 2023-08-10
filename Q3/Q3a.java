package Q3;

public class Q3a {

    public static int maxPoints(int[] targets) {
        int n = targets.length;
        int[] paddedTargets = new int[n + 2];
        System.arraycopy(targets, 0, paddedTargets, 1, n);
        paddedTargets[0] = paddedTargets[n + 1] = 1;

        int[][] dp = new int[n + 2][n + 2];

        for (int len = 1; len <= n; len++) {
            for (int start = 1; start + len - 1 <= n; start++) {
                int end = start + len - 1;
                for (int k = start; k <= end; k++) {
                    dp[start][end] = Math.max(dp[start][end],
                            dp[start][k - 1] + dp[k + 1][end] + paddedTargets[start - 1] * paddedTargets[k] * paddedTargets[end + 1]);
                }
            }
        }

        return dp[1][n];
    }

    public static void main(String[] args) {
        int[] targets = { 3, 1, 5, 8 };
        int result = maxPoints(targets);
        System.out.println("Maximum points: " + result);
    }
}

package Q3;

public class Q3a {
    public static int calculateMaxPoints(int[] targets) {
        int numTargets = targets.length;
        int[] paddedTargets = new int[numTargets + 2];
        paddedTargets[0] = paddedTargets[numTargets + 1] = 1;
        System.arraycopy(targets, 0, paddedTargets, 1, numTargets);

        int[][] maxPoints = new int[numTargets + 2][numTargets + 2];

        for (int len = 1; len <= numTargets; len++) {
            for (int left = 1; left <= numTargets - len + 1; left++) {
                int right = left + len - 1;
                for (int i = left; i <= right; i++) {
                    maxPoints[left][right] = Math.max(maxPoints[left][right],
                            maxPoints[left][i - 1] + paddedTargets[left - 1] * paddedTargets[i] * paddedTargets[right + 1] + maxPoints[i + 1][right]);
                }
            }
        }

        return maxPoints[1][numTargets];
    }

    public static void main(String[] args) {
        int[] targets = {3, 1, 5, 8};
        int result = calculateMaxPoints(targets);
        System.out.println("Maximum points: " + result);
    }
}

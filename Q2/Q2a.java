package Q2;

import java.util.Arrays;

public class Q2a {

    public static int longestSubsequence(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);     // Initializing all lengths to 1, as each element is a valid subsequence by itself


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (Math.abs(nums[i] - nums[j]) <= k) {
                    // Updating the length of the subsequence ending at the current element
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLength = 0;
        // Finding the maximum length among all subsequences
        for (int len : dp) {
            maxLength = Math.max(maxLength, len);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = { 8, 5, 4, 2, 1, 4, 3, 4, 3, 1, 15 };
        int k = 3;
        int output = longestSubsequence(nums, k);
        System.out.println("Output: " + output);
    }
}

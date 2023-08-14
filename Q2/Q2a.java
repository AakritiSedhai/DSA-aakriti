package Q2;

public class Q2a {

    public static int longestSequence(int[] nums, int k) {



        // Initialize a dynamic programming array

        int[] dp = new int[nums.length];

        for (int i = 0; i < dp.length; i++) {

            dp[i] = 1;

        }



        // Iterate over the array

        for (int i = 1; i < nums.length; i++) {

            // Iterate over the previous elements

            for (int j = 0; j < i; j++) {

                // Check if the difference between the current element and the previous element

                // is less than or equal to k and nums[i] < nums[j]

                if (nums[j] - nums[i] <= k && nums[j] > nums[i]) {

                    // Check if the current element can be added to the previous element

                    if (dp[i] < dp[j] + 1) {

                        dp[i] = dp[j] + 1;

                    }



                }

            }

        }



        // Return the maximum value in the dp array

        int max = 0;

        for (int i = 0; i < dp.length; i++) {

            if (dp[i] > max) {

                max = dp[i];



            }

        }

        return max;



    }



    public static void sortDesc(int[] nums) {

        int n = nums.length;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n - i - 1; j++) {

                if (nums[j] < nums[j + 1]) {

                    int temp = nums[j];

                    nums[j] = nums[j + 1];

                    nums[j + 1] = temp;

                }

            }

        }

    }



    public static void main(String[] args) {



        int[] nums = { 8, 5, 4, 2, 1, 4, 3, 4, 3, 1, 15 };

        int k = 3;

        int result = longestSequence(nums, k);



        System.out.println(result);

    }

}


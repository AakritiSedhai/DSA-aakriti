package Q1;

public class Q1a {

    public static int calculateMinimumCost(int[][] clothingPrices) {
        int[][] minCostMatrix = new int[3][3];

        for (int color = 0; color < 3; color++) {
            minCostMatrix[0][color] = clothingPrices[0][color];
        }

        for (int person = 1; person < 3; person++) {
            for (int color = 0; color < 3; color++) {
                int minCostFromPreviousPersons = Math.min(
                        minCostMatrix[person - 1][(color + 1) % 3],
                        minCostMatrix[person - 1][(color + 2) % 3]);
                minCostMatrix[person][color] = clothingPrices[person][color] + minCostFromPreviousPersons;
            }
        }

        int minTotalCost = Integer.MAX_VALUE;
        for (int color = 0; color < 3; color++) {
            minTotalCost = Math.min(minTotalCost, minCostMatrix[2][color]);
        }

        return minTotalCost;
    }

    public static void main(String[] args) {
        int[][] clothingPrices = {
                { 14, 4, 11 },
                { 11, 14, 3 },
                { 14, 2, 10 }
        };
        int result = calculateMinimumCost(clothingPrices);
        System.out.println("The minimum cost required: " + result);
    }
}

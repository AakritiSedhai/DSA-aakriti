package Q5;

import java.util.Random;

public class Q5a {

    // Custom function to optimize
    private static double customFunction(double x) {
        return -(x - 5) * (x - 5); // Minimize
    }

    // Generate a random neighbor within a specified step size
    private static double getRandomNeighbor(double x, double stepSize) {
        Random random = new Random();
        double offset = random.nextDouble() * stepSize;
        if (random.nextBoolean()) {
            return x + offset;
        } else {
            return x - offset;
        }
    }

    // Hill climbing algorithm to find the optimal solution
    public static double hillClimbing(double initialX, double stepSize, int maxIterations) {
        double currentX = initialX;
        double currentValue = customFunction(currentX);

        for (int i = 0; i < maxIterations; i++) {
            // Generate a random neighbor
            double neighborX = getRandomNeighbor(currentX, stepSize);
            double neighborValue = customFunction(neighborX);

            // Move to the neighbor if it has a higher value
            if (neighborValue > currentValue) {
                currentX = neighborX;
                currentValue = neighborValue;
            }
        }

        return currentX;
    }

    public static void main(String[] args) {
        double initialX = 0; // Initial solution
        double stepSize = 0.1; // Step size for random neighbors
        int maxIterations = 100; // Maximum number of iterations

        // Find the optimal solution using hill climbing
        double solution = hillClimbing(initialX, stepSize, maxIterations);

        // Evaluate the optimal value using the custom function
        double optimalValue = customFunction(solution);

        // Print the results
        System.out.println("Optimal Solution: " + solution);
        System.out.println("Optimal Value: " + optimalValue);
    }
}

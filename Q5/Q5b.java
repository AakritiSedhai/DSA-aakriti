package Q5;

import java.util.ArrayList;
import java.util.List;

public class Q5b {

    static List<List<Integer>> graph;
    static boolean[] visited;
    static int[] reverseCount;

    public static int minReorder(int n, int[][] connections) {
        // Initialize the graph and arrays
        initializeGraphAndArrays(n, connections);

        // Perform depth-first search to count reversals
        dfsToCountReversals(0);

        // Calculate the total minimum number of reversals
        int totalReversals = calculateTotalReversals();

        return totalReversals;
    }

    private static void initializeGraphAndArrays(int n, int[][] connections) {
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build the directed graph with positive and negative edges
        for (int[] connection : connections) {
            int from = connection[0];
            int to = connection[1];
            graph.get(from).add(to);        // Positive edge indicates original direction
            graph.get(to).add(-from);       // Negative edge indicates reversed direction
        }

        visited = new boolean[n];
        reverseCount = new int[n];
    }

    private static void dfsToCountReversals(int node) {
        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            if (!visited[Math.abs(neighbor)]) {
                if (neighbor < 0) {
                    reverseCount[node]++;
                    neighbor = -neighbor;
                }
                dfsToCountReversals(neighbor);
                reverseCount[node] += reverseCount[neighbor];
            }
        }
    }

    private static int calculateTotalReversals() {
        int totalReversals = 0;
        for (int count : reverseCount) {
            if (count > 0) {
                totalReversals++;
            }
        }
        return totalReversals;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] connections = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
        int result = minReorder(n, connections);
        System.out.println("Minimum number of reversals: " + result);
    }
}

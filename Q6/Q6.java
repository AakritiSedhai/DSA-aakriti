package Q6;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class Q6 {

    // Custom RecursiveAction class for parallel merge sort
    static class MergeSortTask extends RecursiveAction {
        private int[] array;
        private int start;
        private int end;

        // Constructor to initialize the task
        public MergeSortTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            // Base case: if array size is small, perform sequential merge sort
            if (end - start <= SEQUENTIAL_THRESHOLD) {
                Arrays.sort(array, start, end);
            } else {
                int mid = (start + end) / 2;

                // Create left and right subtasks for parallel sorting
                MergeSortTask leftTask = new MergeSortTask(array, start, mid);
                MergeSortTask rightTask = new MergeSortTask(array, mid, end);

                // Fork the subtasks to work in parallel
                invokeAll(leftTask, rightTask);

                // Merge the sorted subarrays
                merge(array, start, mid, end);
            }
        }

        // Merge two sorted subarrays
        private void merge(int[] array, int start, int mid, int end) {
            int[] merged = new int[end - start];
            int leftIndex = start;
            int rightIndex = mid;
            int mergedIndex = 0;

            while (leftIndex < mid && rightIndex < end) {
                if (array[leftIndex] < array[rightIndex]) {
                    merged[mergedIndex++] = array[leftIndex++];
                } else {
                    merged[mergedIndex++] = array[rightIndex++];
                }
            }

            while (leftIndex < mid) {
                merged[mergedIndex++] = array[leftIndex++];
            }

            while (rightIndex < end) {
                merged[mergedIndex++] = array[rightIndex++];
            }

            System.arraycopy(merged, 0, array, start, merged.length);
        }
    }

    // Threshold for switching to sequential merge sort
    private static final int SEQUENTIAL_THRESHOLD = 6; // 4 to 6

    public static void parallelMergeSort(int[] array) {
        ForkJoinPool pool = new ForkJoinPool();
        MergeSortTask task = new MergeSortTask(array, 0, array.length);
        pool.invoke(task);
    }

    public static void main(String[] args) {
        int[] inputArray = {5, 2, 9, 1, 5, 6, 8, 4};
        parallelMergeSort(inputArray);
        System.out.println("Sorted array: " + Arrays.toString(inputArray));
    }
}

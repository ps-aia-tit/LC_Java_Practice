package com.aiatit.core.basics.practice3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervalsExample {

    /**
     * Merges all overlapping intervals in a given array.
     *
     * @param intervals The input array of intervals, where intervals[i] = [start, end].
     * @return A new array of non-overlapping intervals.
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }

        // Step 1: Sort intervals by their start time [1]
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // Step 2: Iterate through the sorted intervals and merge overlaps [1]
        List<int[]> merged = new ArrayList<>();
        // Add the first interval to the result list to start [1]
        merged.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] currentInterval = intervals[i];
            // Get the last interval added to our merged list [1]
            int[] lastMergedInterval = merged.get(merged.size() - 1);

            // Check for overlap: current interval starts before or at the end of the last merged interval [1]
            if (currentInterval[0] <= lastMergedInterval[1]) {
                // Overlap exists, merge them by extending the end point of the last merged interval [1]
                lastMergedInterval[1] = Math.max(lastMergedInterval[1], currentInterval[1]);
            } else {
                // No overlap, add the current interval as a new, separate interval [1]
                merged.add(currentInterval);
            }
        }

        // Step 3: Convert the result List<int[]> to a 2D int[][] array [1]
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result1 = merge(intervals1);
        System.out.println("Input 1: " + Arrays.deepToString(intervals1));
        System.out.println("Merged 1: " + Arrays.deepToString(result1));
        // Output: [[1, 6], [8, 10], [15, 18]]

        int[][] intervals2 = {{1, 4}, {4, 5}};
        int[][] result2 = merge(intervals2);
        System.out.println("\nInput 2: " + Arrays.deepToString(intervals2));
        System.out.println("Merged 2: " + Arrays.deepToString(result2));
        // Output: [[1, 5]]

        int[][] intervals3 = {{4, 7}, {1, 4}};
        int[][] result3 = merge(intervals3);
        System.out.println("\nInput 3: " + Arrays.deepToString(intervals3));
        System.out.println("Merged 3: " + Arrays.deepToString(result3));
        // Output: [[1, 7]]
    }
}


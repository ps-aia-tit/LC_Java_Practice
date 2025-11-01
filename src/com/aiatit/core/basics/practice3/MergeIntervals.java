package com.aiatit.core.basics.practice3;

/***
 * The "Merge Intervals" problem requires merging all overlapping intervals in a
 * given collection of intervals. The most efficient approach involves first
 * sorting the intervals based on their start times and then iterating through
 * them to merge overlapping ones
 * 
 * Explanation of the Approach:
Sorting: The crucial first step is to sort the intervals by their start times. This ensures that when we iterate through the list, 
any potential overlap must occur with the previous interval we processed.
Merging: We initialize a list of merged intervals with the first sorted interval. Then, we iterate through the rest of the intervals.
For each interval, we check if its start time is less than or equal to the end time of the last interval in our merged list.
If it overlaps: We update the end time of the last interval in merged to be the maximum of the two overlapping end times.
If it does not overlap: We add the current interval to the merged list as a new, separate interval.
Complexity: Sorting takes O(N log N) time. The single pass through the sorted intervals takes O(N) time. 
Therefore, the overall time complexity is dominated by the sort, 
making it O(N log N). The space complexity is O(N) to store the merged intervals. 

 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

	/**
	 * Merges all overlapping intervals.
	 *
	 * @param intervals A 2D array where each sub-array is [start, end].
	 * @return A 2D array of the merged intervals.
	 */
	public static int[][] merge(int[][] intervals) {
		if (intervals == null || intervals.length <= 1) {
			return intervals;
		}

		// 1. Sort the intervals based on their start time
		// Using Java's built-in sort with a custom comparator
		Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
		System.out.println("Original sort intervals 0: " + Arrays.deepToString(intervals));
		List<int[]> merged = new ArrayList<>();
		int[] currentInterval = intervals[0];
		merged.add(currentInterval);

		// 2. Iterate through the sorted intervals and merge overlaps
		for (int[] interval : intervals) {
			int currentEnd = currentInterval[1];
			int nextStart = interval[0];
			int nextEnd = interval[1];

			// If the current interval overlaps with the next interval (next start <=
			// current end)
			if (nextStart <= currentEnd) {
				// Merge the two intervals by updating the current interval's end time
				// to be the maximum of the two end times
				currentInterval[1] = Math.max(currentEnd, nextEnd);
			} else {
				// If the intervals do not overlap, add the new interval to the list
				currentInterval = interval;
				merged.add(currentInterval);
			}
		}

		// 3. Convert the List<int[]> back to a 2D int array
		return merged.toArray(new int[merged.size()][]);
	}

	public static void main(String[] args) {
		int[][] intervals1 = { { 1, 3 },{ 3, 6 },  { 2, 6 }, { 8, 10 }, { 15, 18 } };
		System.out.println("Original intervals 1: " + Arrays.deepToString(intervals1));
		int[][] result1 = merge(intervals1);
		
		System.out.println("Merged intervals 1: " + Arrays.deepToString(result1));
		// Expected Output:

		int[][] intervals2 = { { 1, 4 }, { 4, 5 } };
		System.out.println("\nOriginal intervals 2: " + Arrays.deepToString(intervals2));
		int[][] result2 = merge(intervals2);
		
		System.out.println("Merged intervals 2: " + Arrays.deepToString(result2));
		// Expected Output:
	}
}

package com.aiatit.core.basics.practice1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

	/**
	 * Finds the indices of two numbers that add up to the target. Uses a
	 * single-pass HashMap approach for O(n) time complexity.
	 *
	 * @param nums   The array of integers.
	 * @param target The target sum.
	 * @return An array containing the indices of the two numbers.
	 * @throws IllegalArgumentException If no two sum solution is found.
	 */
	public static int[] twoSum(int[] nums, int target) {
		// A HashMap to store the "complement" (target - current_number) and its index
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];

			// Check if the required complement is already in the map
			if (map.containsKey(complement)) {
				// If found, we have the two numbers. Return their indices.
				return new int[] { map.get(complement), i };
			}

			// If not found, put the current number and its index into the map
			map.put(nums[i], i);
		}

		// If the loop finishes without finding a pair
		throw new IllegalArgumentException("No two sum solution found");
	}

	public static void main(String[] args) {
		int[] nums = { 2, 7, 11, 15 };
		int target = 9;

		try {
			int[] result = twoSum(nums, target);
			System.out.println("Input Array: " + Arrays.toString(nums));
			System.out.println("Target: " + target);
			System.out.println("Indices: " + Arrays.toString(result)); // Output: [0, 1]
			System.out.println("Numbers found: " + nums[result[0]] + " and " + nums[result[1]]);

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}

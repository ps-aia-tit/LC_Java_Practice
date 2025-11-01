package com.aiatit.core.basics.practice2;

import java.util.*;

/**
 * * Finds the k most frequent elements in the array. Uses HashMap for frequency
 * counting and PriorityQueue (Min-Heap) for top K selection. Time complexity:
 * O(n log k)
 */
public class TopKFrequentElements {

	/**
	 * Finds the k most frequent elements in the array. Uses HashMap for frequency
	 * counting and PriorityQueue (Min-Heap) for top K selection. Time complexity:
	 * O(n log k)
	 *
	 * @param nums The input array of integers.
	 * @param k    The number of most frequent elements to return.
	 * @return An array of the top k frequent elements.
	 */
	public static int[] topKFrequent(int[] nums, int k) {
		// Step 1: Count the frequency of each element using a HashMap
		Map<Integer, Integer> frequencyMap = new HashMap<>();
		for (int num : nums) {
			frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
		}

		// Step 2: Use a Min-Heap (PriorityQueue) to keep track of the k most frequent
		// elements
		// The comparator orders entries by frequency in ascending order (smallest
		// frequency at the top)
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(
				(num1, num2) -> frequencyMap.get(num1) - frequencyMap.get(num2));

		// Iterate through the unique elements in the frequency map
		for (int num : frequencyMap.keySet()) {
			minHeap.add(num);
			// If the heap size exceeds k, remove the element with the smallest frequency
			if (minHeap.size() > k) {
				minHeap.poll();
			}
		}

		// Step 3: Extract elements from the heap to build the result array
		int[] result = new int[k];
		for (int i = k - 1; i >= 0; i--) {
			result[i] = minHeap.poll();
		}

		return result;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 1, 1, 2, 2, 3 };
		int k = 2;
		int[] result = topKFrequent(nums, k);
		System.out.println("Input Array: " + Arrays.toString(nums));
		System.out.println("k: " + k);
		System.out.println("Top K Frequent Elements: " + Arrays.toString(result)); // Output: [1, 2] or [2, 1]

		int[] nums2 = { 4, 1, -1, 2, -1, 2, 3 };
		int k2 = 2;
		int[] result2 = topKFrequent(nums2, k2);
		System.out.println("\nInput Array 2: " + Arrays.toString(nums2));
		System.out.println("k: " + k2);
		System.out.println("Top K Frequent Elements 2: " + Arrays.toString(result2)); // Output: [-1, 2]
	}
}

package com.aiatit.core.basics.practice1;

import java.util.*;

public class TopKFrequentElements {

    /**
     * Finds the k most frequent elements in an array.
     *
     * @param nums The input array.
     * @param k The number of frequent elements to return.
     * @return An array containing the k most frequent elements.
     */
    public static int[] topKFrequent(int[] nums, int k) {
        // 1. Count the frequency of each number using a HashMap
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        // 2. Use a Min-Heap (PriorityQueue) to keep track of the top k elements
        // The heap stores elements as arrays [number, frequency].
        // It is a Min-Heap based on frequency (the second element of the array).
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();

            minHeap.add(new int[]{num, count});

            // If the heap size exceeds k, remove the smallest frequency element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // 3. Extract the elements from the min-heap into the result array
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll()[0];
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int[] result1 = topKFrequent(nums1, k1);
        System.out.println("Input: " + Arrays.toString(nums1) + ", k: " + k1);
        System.out.println("Top " + k1 + " frequent elements: " + Arrays.toString(result1)); 
        // Output: [1, 2] or [2, 1] - order might vary

        int[] nums2 = {1};
        int k2 = 1;
        int[] result2 = topKFrequent(nums2, k2);
        System.out.println("\nInput: " + Arrays.toString(nums2) + ", k: " + k2);
        System.out.println("Top " + k2 + " frequent elements: " + Arrays.toString(result2)); 
        // Output: [1]
    }
}


package com.aiatit.core.basics.practice3;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {

    /**
     * Finds the length of the longest substring without repeating characters.
     * Uses a sliding window with a HashMap.
     * Time Complexity: O(n)
     *
     * @param s The input string.
     * @return The length of the longest substring.
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // HashMap to store characters and their *indices*
        // Key: character, Value: index in the string
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int left = 0; // Left pointer of the sliding window

        // Right pointer of the sliding window (i)
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If the current character is already in the map, and its last
            // occurrence is within our current window (>= left)
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= left) {
                // Move the left pointer past the last occurrence of the repeating character
                left = charIndexMap.get(currentChar) + 1;
            }

            // Update the map with the current character's index
            charIndexMap.put(currentChar, right);

            // Calculate the current window length (right - left + 1)
            // and update maxLength if necessary
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        System.out.println("String: \"" + s1 + "\"");
        System.out.println("Length of longest substring: " + lengthOfLongestSubstring(s1)); // Output: 3 ("abc")

        String s2 = "bbbbb";
        System.out.println("\nString: \"" + s2 + "\"");
        System.out.println("Length of longest substring: " + lengthOfLongestSubstring(s2)); // Output: 1 ("b")

        String s3 = "pwwkew";
        System.out.println("\nString: \"" + s3 + "\"");
        System.out.println("Length of longest substring: " + lengthOfLongestSubstring(s3)); // Output: 3 ("wke" or "kew")

        String s4 = "dvdf";
        System.out.println("\nString: \"" + s4 + "\"");
        System.out.println("Length of longest substring: " + lengthOfLongestSubstring(s4)); // Output: 3 ("vdf")
    }
}


package com.aiatit.core.basics.practice3;

public class PalindromeChecker {

    public static boolean isPalindrome(String s) {
        // Handle null or empty strings
        if (s == null || s.isEmpty()) {
            return true;
        }

        // Convert the string to lowercase and remove all non-alphanumeric characters
        String cleanString = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int left = 0;
        int right = cleanString.length() - 1;

        // Iterate from both ends toward the center
        while (left < right) {
            // If characters at the current pointers do not match, it's not a palindrome
            if (cleanString.charAt(left) != cleanString.charAt(right)) {
                return false;
            }
            // Move pointers inward
            left++;
            right--;
        }

        // If the loop completes without finding mismatches, it is a palindrome
        return true;
    }

    public static void main(String[] args) {
        String test1 = "racecar";
        String test2 = "A man, a plan, a canal: Panama";
        String test3 = "hello";
        String test4 = "12321";

        System.out.println(test1 + " is a palindrome: " + isPalindrome(test1));
        System.out.println(test2 + " is a palindrome: " + isPalindrome(test2));
        System.out.println(test3 + " is a palindrome: " + isPalindrome(test3));
        System.out.println(test4 + " is a palindrome: " + isPalindrome(test4));
    }
}


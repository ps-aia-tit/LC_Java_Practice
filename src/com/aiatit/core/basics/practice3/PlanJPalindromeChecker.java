package com.aiatit.core.basics.practice3;

public class PlanJPalindromeChecker {

    /**
     * Checks if a string is a palindrome, ignoring non-alphanumeric characters
     * and case sensitivity.
     * @param s The input string.
     * @return true if the string is a palindrome, false otherwise.
     */
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // Move left pointer past non-alphanumeric characters
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // Move right pointer past non-alphanumeric characters
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Compare characters case-insensitively
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            // Move pointers inward for the next comparison
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        String word1 = "madam";
        String phrase1 = "A man, a plan, a canal: Panama!";
        String word2 = "hello";

        System.out.println("\"" + word1 + "\" is a palindrome: " + isPalindrome(word1));
        System.out.println("\"" + phrase1 + "\" is a palindrome: " + isPalindrome(phrase1));
        System.out.println("\"" + word2 + "\" is a palindrome: " + isPalindrome(word2));
    }
}


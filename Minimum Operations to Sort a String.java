/*
You are given a string s consisting of lowercase English letters.

In one operation, you can select any substring of s that is not the entire string and sort it in non-descending alphabetical order.

Return the minimum number of operations required to make s sorted in non-descending order. If it is not possible, return -1.

 

Example 1:

Input: s = "dog"

Output: 1

Explanation:​​​​​​​

Sort substring "og" to "go".
Now, s = "dgo", which is sorted in ascending order. Thus, the answer is 1.
Example 2:

Input: s = "card"

Output: 2

Explanation:

Sort substring "car" to "acr", so s = "acrd".
Sort substring "rd" to "dr", making s = "acdr", which is sorted in ascending order. Thus, the answer is 2.
Example 3:

Input: s = "gf"

Output: -1

Explanation:

It is impossible to sort s under the given constraints. Thus, the answer is -1.
 

Constraints:

1 <= s.length <= 105
s consists of only lowercase English letters.
*/

class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        boolean isSorted = true;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) > s.charAt(i + 1)) {
                isSorted = false;
                break;
            }
        }
        if (isSorted) {
            return 0;
        }
        
        if (n < 3) {
            return -1;
        }
        
        char minChar = 'z';
        char maxChar = 'a';
        int minCount = 0;
        int maxCount = 0;
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c < minChar) {
                minChar = c;
            }
            if (c > maxChar) {
                maxChar = c;
            }
        }
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == minChar) minCount++;
            if (c == maxChar) maxCount++;
        }
        
        if (s.charAt(0) == minChar || s.charAt(n - 1) == maxChar) {
            return 1;
        }
        
        if (s.charAt(0) == maxChar && maxCount == 1 && s.charAt(n - 1) == minChar && minCount == 1) {
            return 3;
        }
        
        return 2;
    }
}

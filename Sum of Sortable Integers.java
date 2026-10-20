/*
You are given an integer array nums of length n.

An integer k is called sortable if k divides n and you can sort nums in non-decreasing order by sequentially performing the following operations:

Partition nums into consecutive subarrays of length k.
Cyclically rotate each subarray independently any number of times to the left or to the right.
Return an integer denoting the sum of all possible sortable integers k.

 

Example 1:

Input: nums = [3,1,2]

Output: 3

Explanation:​​​​​​​

For n = 3, possible divisors are 1 and 3.
For k = 1: each subarray has one element. No rotation can sort the array.
For k = 3: the single subarray [3, 1, 2] can be rotated once to produce [1, 2, 3], which is sorted.
Only k = 3 is sortable. Hence, the answer is 3.
Example 2:

Input: nums = [7,6,5]

Output: 0

Explanation:

For n = 3, possible divisors are 1 and 3.
For k = 1: each subarray has one element. No rotation can sort the array.
For k = 3: the single subarray [7, 6, 5] cannot be rotated into non-decreasing order.
No k is sortable. Hence, the answer is 0.
Example 3:

Input: nums = [5,8]

Output: 3

Explanation:​​​​​​​

For n = 2, possible divisors are 1 and 2.
Since [5, 8] is already sorted, every divisor is sortable. Hence, the answer is 1 + 2 = 3.
 

Constraints:

1 <= n == nums.length <= 105
1 <= nums[i] <= 105
*/

import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        
        int totalSum = 0;
        for (int k = 1; k <= n; ++k) {
            if (n % k == 0) {
                if (check(nums, sortedNums, n, k)) {
                    totalSum += k;
                }
            }
        }
        return totalSum;
    }
    
    private boolean check(int[] nums, int[] sortedNums, int n, int k) {
        for (int i = 0; i < n; i += k) {
            int dropCount = 0;
            int dropIdx = -1;
            
            for (int j = 0; j < k; ++j) {
                int curr = nums[i + j];
                int next = nums[i + (j + 1) % k];
                if (curr > next) {
                    dropCount++;
                    dropIdx = j;
                }
            }
            
            if (dropCount > 1) return false;
            
            int startOffset = (dropCount == 1) ? (dropIdx + 1) % k : 0;
            
            for (int j = 0; j < k; ++j) {
                if (nums[i + (startOffset + j) % k] != sortedNums[i + j]) {
                    return false;
                }
            }
        }
        return true;
    }
}

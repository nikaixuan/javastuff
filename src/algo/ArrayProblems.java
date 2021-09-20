package algo;

import java.util.HashMap;
import java.util.Map;

public class ArrayProblems {

    // 74
    // also can apply binary search on [0, m*n-1]
    public boolean searchMatrix(int[][] matrix, int target) {
        int len = matrix.length;

        int left = 0, right = len-1;
        while (left<=right) {
            int mid = left + (right-left)/2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        int index = right;
        if (index<0) return false;
        left = 0;
        right = matrix[index].length-1;
        while (left<=right) {
            int mid = left + (right-left)/2;
            if (matrix[index][mid] == target) {
                return true;
            } else if (matrix[index][mid] < target) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return false;
    }

    // 659
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> need = new HashMap<>();

        for (int num: nums) {
            freq.put(num, freq.getOrDefault(num, 0)+1);
        }

        for (int num : nums) {
            if (freq.get(num).equals(0)) {
                continue;
            }
            if (need.getOrDefault(num, 0)>0) {
                freq.put(num, freq.get(num)-1);
                need.put(num, need.get(num)-1);
                need.put(num+1, need.getOrDefault(num+1, 0)+1);
            } else if (freq.getOrDefault(num, 0)>0 && freq.getOrDefault(num+1,0) > 0 && freq.getOrDefault(num+2,0) > 0) {
                freq.put(num, freq.get(num)-1);
                freq.put(num+1, freq.get(num+1)-1);
                freq.put(num+2, freq.get(num+2)-1);
                need.put(num+3, need.getOrDefault(num+3, 0)+1);
            } else {
                return false;
            }
        }
        return true;
    }
}

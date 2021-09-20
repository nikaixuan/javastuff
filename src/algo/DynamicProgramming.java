package algo;

import javafx.util.Pair;

import java.util.*;

public class DynamicProgramming {

    // 322
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i=1;i< dp.length;i++) {
            for (int j=0;j<coins.length;j++) {
                if (i-coins[j]>=0) {
                    System.out.println(dp[i]);
                    dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
                }
            }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }

    public int coinChangeTopdown(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int r = Integer.MAX_VALUE;
        for (int i=0;i<coins.length;i++) {
            if (amount-coins[i]>=0) {
                r = Math.min(r, coinChangeTopdown(coins, amount-coins[i])+1);
            }
        }
        return r==Integer.MAX_VALUE?-1:r;
    }

    // 931
    public int minFallingPathSum(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        dp[0] = matrix[0];

        for (int i=1;i< matrix.length;i++) {
            for (int j=0;j< matrix[0].length;j++) {
                dp[i][j] = dp[i-1][j]+matrix[i][j];
                if (j!=0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]+matrix[i][j]);
                }
                if (j!= matrix[0].length-1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j+1]+matrix[i][j]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i=0;i<dp[dp.length-1].length;i++) {
            res = Math.min(res, dp[dp.length-1][i]);
        }
        return res;
    }

    // 72
    public int minDistance72(String word1, String word2) {
        int[][] dp = new int[word2.length()+1][word1.length()+1];
        for (int i=0;i< dp.length;i++) {
            dp[i][0] = i;
        }
        for (int j=0;j< dp[0].length;j++) {
            dp[0][j] = j;
        }

        for (int i=1;i<=word2.length();i++) {
            for (int j=1;j<=word1.length();j++) {
                if (word1.charAt(j-1) == word2.charAt(i-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int minValue = Math.min(dp[i][j-1], dp[i-1][j]);
                    dp[i][j] = Math.min(minValue, dp[i-1][j-1]) + 1;
                }
                System.out.println(dp[i][j]);
            }
        }
        return dp[word2.length()][word1.length()];
    }

    // 300
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int res = 1;
        Arrays.fill(dp, 1);
        for (int i=1;i<nums.length;i++) {
            for (int j=0;j<i;j++) {
                if (nums[i]>nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 354
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (o1, o2) -> o1[0]==o2[0]?o2[1]-o1[0]:o1[0]-o2[0]);

        int[] dp = new int[envelopes.length];
        int res = 1;
        Arrays.fill(dp, 1);
        for (int i=1;i<envelopes.length;i++) {
            for (int j=0;j<i;j++) {
                if (envelopes[i][0]>envelopes[j][0]&&envelopes[i][1]>envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 53
    public int maxSubArray(int[] nums) {
        int a = nums[0];
        int b = 0;
        int res = a;
        for (int i=1;i<nums.length;i++) {
            b = Math.max(nums[i], nums[i]+a);
            a = b;
            res = Math.max(res, a);
        }
        return res;
    }

    // 1143
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];

        for (int i=1;i<=text1.length();i++) {
            for (int j=1;j<=text2.length();j++) {
                if (text1.charAt(i-1)==text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    // 583
    public int minDistance583(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i=0;i< dp.length;i++) {
            dp[i][0] = i;
        }
        for (int j=0;j< dp[0].length;j++) {
            dp[0][j] = j;
        }

        for (int i=1;i<=word1.length();i++) {
            for (int j=1;j<=word2.length();j++) {
                if (word1.charAt(i-1)==word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    // 712
    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for (int i=1;i< dp.length;i++) {
            dp[i][0] = s1.charAt(i-1)+dp[i-1][0];
        }
        for (int j=1;j< dp[0].length;j++) {
            dp[0][j] = s2.charAt(j-1)+dp[0][j-1];
        }

        for (int i=1;i<=s1.length();i++) {
            for (int j=1;j<=s2.length();j++) {
                if (s1.charAt(i-1)==s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i][j-1]+s2.charAt(j-1), dp[i-1][j]+s1.charAt(i-1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    // 516
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for (int i=0;i<s.length();i++) {
            dp[i][i] = 1;
        }
        for (int i=s.length()-1;i>=0;i--) {
            for (int j=i+1;j<s.length();i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }

    // 5
    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        String res = "";
        for (int i=0;i<s.length();i++) {
            dp[i][i] = true;
        }

        for (int i=s.length()-1;i>=0;i--) {
            for (int j = i;j<s.length();j++) {
                if (s.charAt(i) == s.charAt(j) && (j-i<2 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                }
                if (dp[i][j] && j-i+1>res.length()) {
                    res = s.substring(i, j+1);
                }
            }
        }
        return res;
    }

    // 377
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i=1;i<=target;i++) {
            for (int j=0;j<nums.length;j++) {
                if (i>=nums[j]) {
                    dp[i] += dp[i-nums[j]];
                }
            }
        }
        return dp[target];
    }

    // 416
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        System.out.println(sum);
        if (sum%2==1) return false;

        int target = sum/2;
        boolean[] dp = new boolean[target+1];
        dp[0] = true;

        for (int i=0;i<nums.length;i++) {
            for (int j=target;j>=0;j--) {
                if (j-nums[i]>=0) {
                    dp[j] = dp[j-nums[i]] || dp[j];
                }
            }
        }

        return dp[target];
    }

    // 518
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];
        for (int i=0;i<=coins.length;i++) {
            dp[i][0] = 1;
        }
        for (int i=1;i<=coins.length;i++) {
            for (int j=1;j<=amount;j++) {
                if (j-coins[i-1]>=0) {
                    dp[i][j] = dp[i][j-coins[i-1]] + dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[coins.length][amount];
    }

    // 435
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));

        int end = intervals[0][1];
        int count = 0;
        for (int i=1;i<intervals.length;i++) {
            if (end<=intervals[i][0]) {
                end = intervals[i][1];
            } else {
                count++;
            }
        }
        return count;
    }

    // 1024
    public int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, (o1, o2)->o1[0]==o2[0]?o2[1]-o1[1]:o1[0]-o2[0]);

        int curr = 0, next = 0;
        int i=0, n = clips.length, res = 0;
        while (i< n && clips[i][0]<=curr) {
            while (i< n && clips[i][0]<=curr) {
                next = Math.max(next, clips[i][1]);
                i++;
            }
            res++;
            curr = next;
            if (curr>=time) {
                return res;
            }
        }
        return -1;
    }

    // 64
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i=0;i<dp[0].length;i++) {
            for (int j=0;j< grid.length;j++) {
                if (i<1&&j<1) {
                    dp[i][j] = grid[i][j];
                } else if (i<1){
                    dp[j][i] = dp[j-1][i] + grid[j][i];
                } else if (j<1) {
                    dp[j][i] = dp[j][i-1] + grid[j][i];
                } else {
                    dp[i][j] = Math.min(dp[j-1][i], dp[j][i-1]) + grid[j][i];
                }
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }

    // 174
    // from end point back to start
    public int calculateMinimumHP(int[][] dungeon) {
        int[][] dp = new int[dungeon.length][dungeon[0].length];

        for (int i=dungeon[0].length-1;i>=0;i--) {
            for (int j=dungeon.length-1;j>=0;j--) {
                if (j==dungeon.length-1 && i==dungeon[0].length-1) {
                    if (dungeon[j][i]<0) {
                        dp[j][i] = 1-dungeon[j][i];
                    } else {
                        dp[j][i] = 1;
                    }
                } else if (i==dungeon[0].length-1) {
                    if (dungeon[j][i]<0) {
                        dp[j][i] = dp[j+1][i]-dungeon[j][i];
                    } else {
                        if (dungeon[j][i]>=dp[j+1][i]) {
                            dp[j][i] = 1;
                        } else {
                            dp[j][i] = dp[j+1][i]-dungeon[j][i];
                        }
                    }
                } else if (j==dungeon.length-1) {
                    if (dungeon[j][i]<0) {
                        dp[j][i] = dp[j][i+1]-dungeon[j][i];
                    } else {
                        if (dungeon[j][i]>=dp[j][i+1]) {
                            dp[j][i] = 1;
                        } else {
                            dp[j][i] = dp[j][i+1]-dungeon[j][i];
                        }
                    }
                } else {
                    int min = Math.min(dp[j][i+1], dp[j+1][i]);
                    if (dungeon[j][i]<0) {
                        dp[j][i] = min-dungeon[j][i];
                    } else {
                        if (dungeon[j][i]>=min) {
                            dp[j][i] = 1;
                        } else {
                            dp[j][i] = min-dungeon[j][i];
                        }
                    }
                }
            }
        }
        return dp[0][0];
    }

    // 514
    public int findRotateSteps(String ring, String key) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i=0;i<ring.length();i++) {
            map.putIfAbsent(ring.charAt(i), new ArrayList<>());
            map.get(ring.charAt(i)).add(i);
        }
        int[][] dp = new int[key.length()+1][ring.length()];

        for (int i=key.length()-1;i>=0;i--) {
            for (int j=0;j<ring.length();j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int index: map.get(key.charAt(i))) {
                    int diff = Math.abs(j-index);
                    int dist = Math.min(diff, ring.length()-diff);
                    dp[i][j] = Math.min(dp[i][j], dp[i+1][index]+dist);
                }
            }
        }
        return dp[0][0]+key.length();
    }

    // 887 TLE DP
        Map<String, Integer> map;
        public int superEggDrop(int k, int n) {
            map = new HashMap<>();
            return eggDrop(k, n);
        }

        public int eggDrop(int k, int n) {
            if (k==1) return n;
            if (n==0) return 0;
            if (map.containsKey(Integer.toString(k)+ n)) {
                return map.get(Integer.toString(k)+ n);
            }

            int res = Integer.MAX_VALUE;
            for (int i=1;i<=n;i++) {
                // worst case, so should be max among two cases
                res = Math.min(res, Math.max(eggDrop(k-1, i-1), eggDrop(k, n-i))+1);
            }
            map.put(Integer.toString(k)+ n, res);
            return res;
        }
}

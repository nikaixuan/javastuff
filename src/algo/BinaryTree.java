package algo;

import javafx.util.Pair;

import java.util.*;

public class BinaryTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // 129
    int sumres;
    public int sumNumbers(TreeNode root) {
        sumres = 0;
        sumDfs(root, 0);
        return sumres;
    }

    private void sumDfs(TreeNode root, int n) {
        if (root==null) return;
        int val = n*10+root.val;
        if (root.left == null && root.right == null) sumres+=val;
        sumDfs(root.left, val);
        sumDfs(root.right, val);
    }

    // 508
    Map<Integer, Integer> freqmap;
    int freqmax = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        freqmap = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        postOrderFreq(root);
        for (Map.Entry<Integer, Integer> entry: freqmap.entrySet()) {
            if (entry.getValue() == freqmax) {
                res.add(entry.getKey());
            }
        }
        return res.stream().mapToInt(i->i).toArray();
    }

    public int postOrderFreq(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = postOrderFreq(root.left);
        int right = postOrderFreq(root.right);
        int sum = left + right + root.val;
        int freq = freqmap.getOrDefault(sum, 0)+1;
        freqmax = Math.max(freqmax, freq);
        freqmap.put(sum, freq);
        return sum;
    }

    // 113
    List<List<Integer>> pathSumRes;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        pathSumRes = new ArrayList<>();
        pathSumDfs(root, targetSum, new ArrayList<>());
        return pathSumRes;
    }

    public void pathSumDfs(TreeNode root, int targetSum, List<Integer> list) {
        if (root == null)
            return;
        if (root.left == null && root.right== null && targetSum - root.val == 0) {
            list.add(root.val);
            pathSumRes.add(new ArrayList<>(list));
            return;
        }
        list.add(root.val);
        if (root.left!=null) {
            pathSumDfs(root.left, targetSum- root.val, list);
            list.remove(list.size()-1);
        }
        if (root.right!=null) {
            pathSumDfs(root.right, targetSum- root.val, list);
            list.remove(list.size()-1);
        }
    }

    // 513
    // bfs
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int res = 0;
        queue.offer(root);
        while (queue.size()>0) {
            int len = queue.size();
            for (int i=0;i<len;i++) {
                TreeNode curr = queue.poll();
                if (i==len-1) res = curr.val;
                if (curr.right!=null) queue.offer(curr.right);
                if (curr.left!=null) queue.offer(curr.left);
            }
        }
        return res;
    }

    // dfs
    public int findBottomLeftValuedfs(TreeNode root) {
        return postOrderFindLeft(root).getValue();
    }

    public Pair<Integer, Integer> postOrderFindLeft(TreeNode root) {
        if (root == null) return new Pair<>(0, 0);
        if (root.left == null && root.right == null) return new Pair<>(1, root.val);
        Pair<Integer, Integer> left = postOrderFindLeft(root.left);
        Pair<Integer, Integer> right = postOrderFindLeft(root.right);
        if (left.getKey()>=right.getKey()) {
            return new Pair<>(left.getKey()+1, left.getValue());
        } else {
            return new Pair<>(right.getKey()+1, right.getValue());
        }
    }

    // 429
    public List<List<Integer>> levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        queue.offer(root);
        while (queue.size()>0) {
            int len = queue.size();
            List<Integer> subRes = new ArrayList<>();
            for (int i=0;i<len;i++) {
                Node curr = queue.poll();
                if (curr == null) continue;
                subRes.add(curr.val);
                for (Node child : curr.children) {
                    queue.offer(child);
                }
            }
            res.add(subRes);
        }
        return res;
    }

    // 1161
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        int res=0, level = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int l = queue.size();
            int sum = 0;
            level++;
            for (int i=0;i<l;i++) {
                TreeNode curr = queue.poll();
                sum += curr.val;
                if (curr.left!=null) {
                    queue.offer(curr.left);
                }
                if (curr.right!=null) {
                    queue.offer(curr.right);
                }
            }
            if (max<sum) {
                max = sum;
                res = level;
            }
        }
        return res;
    }

    // 1302
    // bfs
    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int l = queue.size();
            int sum = 0;
            for (int i=0;i<l;i++) {
                TreeNode curr = queue.poll();
                sum += curr.val;
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            if (queue.isEmpty()) {
                res = sum;
            }
        }
        return res;
    }

    // dfs
    public int deepestLeavesSum2(TreeNode root) {
        return leaveSumDfs(root).getValue();
    }

    public Pair<Integer, Integer> leaveSumDfs(TreeNode root) {
        if (root==null) {
            return new Pair<>(0, 0);
        }
        if (root.right==null && root.left==null) {
            return new Pair<>(1, root.val);
        }
        Pair<Integer, Integer> left = leaveSumDfs(root.left);
        Pair<Integer, Integer> right = leaveSumDfs(root.right);

        if (left.getKey().equals(right.getKey())) return new Pair<>(left.getKey()+1, left.getValue()+right.getValue());
        if (left.getKey()>right.getKey()) {
            return new Pair<>(left.getKey()+1, left.getValue());
        } else {
            return new Pair<>(right.getKey()+1, right.getValue());
        }
    }
}

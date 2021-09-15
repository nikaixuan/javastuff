package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Backtracking {

    // 386
    List<Integer> reslex;
    public List<Integer> lexicalOrder(int n) {
        reslex = new ArrayList<>();
        for (int i=1;i<10;i++) lexDfs(n, i);
        return reslex;
    }

    private void lexDfs(int n, int factor) {
        if (factor > n) return;
        reslex.add(factor);
        for (int i=0;i<=9;i++) {
            if (factor*10+i>n) return;
            lexDfs(n, factor*10+i);
        }
    }

    // 547
    public int findCircleNum(int[][] isConnected) {
        boolean[] map = new boolean[isConnected.length];
        int res = 0;

        for (int i=0;i< isConnected.length;i++) {
            if (!map[i]) {
                res++;
                finddfs(isConnected, i, map);
            }
        }
        return res;
    }

    public void finddfs(int[][] isConnected, int index, boolean[] map) {
        if (index == isConnected.length) return;
        for (int i=0;i< isConnected[0].length;i++) {
            if (!map[i] && isConnected[index][i]==1) {
                map[i] = true;
                finddfs(isConnected, i, map);
            }
        }
    }

    // 200
    public int numIslands(char[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (grid[i][j] == '1') {
                    res++;
                    islandDfs(grid, i, j);
                }
            }
        }
        return res;
    }

    public void islandDfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i>m-1 || i<0 || j>n-1||j<0||grid[i][j]=='0') {
            return;
        }
        grid[i][j] = '0';
        islandDfs(grid, i+1, j);
        islandDfs(grid, i, j-1);
        islandDfs(grid, i-1, j);
        islandDfs(grid, i, j+1);
    }

    // 130
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        for (int i=0;i<m;i++){
            if (board[i][0]=='O') {
                surroundDfs(board, i, 0);
            }
            if (board[i][n-1]=='O') {
                surroundDfs(board, i, n-1);
            }
        }
        for (int i=0;i<n;i++){
            if (board[0][i]=='O') {
                surroundDfs(board, 0, i);
            }
            if (board[m-1][i]=='O') {
                surroundDfs(board, m-1, i);
            }
        }
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (board[i][j]=='O') {
                    board[i][j]='X';
                } else if (board[i][j]=='Y') {
                    board[i][j]='O';
                }
            }
        }
    }

    private void surroundDfs(char[][] board, int i, int j) {
        int m = board.length;
        int n = board[0].length;
        if (i> m-1||i<0||j>n-1||j<0||board[i][j]!='O') {
            return;
        }
        if (board[i][j] == 'O') {
            board[i][j] = 'Y';
        }
        surroundDfs(board, i+1, j);
        surroundDfs(board, i, j+1);
        surroundDfs(board, i-1, j);
        surroundDfs(board, i, j-1);
    }

    // 79
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] map = new boolean[m][n];
        char[] w = word.toCharArray();
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (existDfs(board, map, w, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existDfs(char[][] board, boolean[][] v, char[] word, int i, int j, int n) {
        if (board[i][j] == word[n]) {
            if (n == word.length-1) {
                return true;
            }
            v[i][j] = true;
            if (i>0 && !v[i-1][j]) {
                boolean res = existDfs(board, v, word, i-1, j, n+1);
                if (res) {
                    return true;
                }
            }
            if (i< board.length-1 && !v[i+1][j]) {
                boolean res = existDfs(board, v, word, i+1, j, n+1);
                if (res) {
                    return true;
                }
            }
            if (j>0 && !v[i][j-1]) {
                boolean res = existDfs(board, v, word, i, j-1, n+1);
                if (res) {
                    return true;
                }
            }
            if (j< board[0].length-1 && !v[i][j+1]) {
                boolean res = existDfs(board, v, word, i, j+1, n+1);
                if (res) {
                    return true;
                }
            }
            v[i][j] = false;
        }
        return false;
    }
}

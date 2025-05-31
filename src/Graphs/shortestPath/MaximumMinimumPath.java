package Graphs.shortestPath;

import java.util.PriorityQueue;

/*
You are given a 2D grid of integers. You start from the top-left cell (0, 0) and want to reach the bottom-right cell (m-1, n-1).
From each cell, you can move in 4 directions: up, down, left, right.
Your goal is to maximize the minimum value along any path from start to end.

🔍 Example:
Input:
grid = [[5,4,5],
        [1,2,6],
        [7,4,6]]
Output:
4
Explanation:
One optimal path is 5 → 4 → 5 → 6 → 6. The minimum value on this path is 4, which is the maximum you can achieve.

✅ Intuition:
We want the path from top-left to bottom-right that has the highest minimum value along the way.
This is similar to Dijkstra’s algorithm, but instead of minimizing distance, we are trying to maximize the minimum value along the path.

 */

public class MaximumMinimumPath {
    public int maximumMinimumPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> b[0] - a[0]); // max heap on value

        pq.offer(new int[]{grid[0][0], 0, 0});
        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        visited[0][0] = true;

        int minVal = grid[0][0];

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int val = curr[0], r = curr[1], c = curr[2];
            minVal = Math.min(minVal, val);

            if (r == m - 1 && c == n - 1) return minVal;

            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr >= 0 && nc >= 0 && nr < m && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    pq.offer(new int[]{grid[nr][nc], nr, nc});
                }
            }
        }

        return -1;
    }
}

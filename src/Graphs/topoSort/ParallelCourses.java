package Graphs.topoSort;

import java.util.*;

/*
You’re given:
An integer n → total number of courses labeled from 1 to n.
A list of prerequisite pairs relations, where each pair [a, b] means course a must be taken before course b.
🏁 Your goal is:
Return the minimum number of semesters required to complete all courses if you can take any number of courses in one semester as long as their prerequisites are met.

🔸 Example:
Input: n = 3, relations = [[1,3],[2,3]]
Output: 2

✅ Explanation:
Take courses 1 and 2 in semester 1.
Then take course 3 in semester 2.

🔸 Constraints Recap:
You can take any number of courses per semester, as long as all prerequisites are completed.
If it's not possible to complete all courses (cycle exists), return -1.
 */


public class ParallelCourses {
    public int minimumSemesters(int n, int[][] relations) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] rel : relations) {
            graph.get(rel[0]).add(rel[1]);
            indegree[rel[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        int semester = 0, taken = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            semester++;
            for (int i = 0; i < size; i++) {
                int course = queue.poll();
                taken++;
                for (int neighbor : graph.get(course)) {
                    indegree[neighbor]--;
                    if (indegree[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return taken == n ? semester : -1;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] relations = {
                {1, 3},
                {2, 3}
        };

        ParallelCourses sol = new ParallelCourses();
        int result = sol.minimumSemesters(n, relations);
        System.out.println("Minimum semesters required: " + result);
    }
}

package Graphs.topoSort;

import java.util.*;

/*
You are given:
An original sequence org (e.g. [1,2,3])
A list of sequences seqs (e.g. [[1,2], [1,3]])

Each sequence in seqs is a partial ordering (i.e., a piece of the full order).

🧩 Your task is:
👉 Determine whether org is the only possible shortest supersequence that can be formed from seqs.

A supersequence of a sequence is a sequence that contains the original sequence as a subsequence and maintains the order.

🔸 Example:
Input: org = [1,2,3], seqs = [[1,2],[1,3]]
Output: false

Why?
From seqs, we get constraints:
1 < 2
1 < 3
But no info about 2 < 3 or 3 < 2, so both [1,2,3] and [1,3,2] are valid.
So, org is not uniquely reconstructible.

🔸 Another Example:
Input: org = [1,2,3], seqs = [[1,2],[2,3]]
Output: true
1 < 2, 2 < 3 → only valid order: [1,2,3] ✅
 */

public class SequenceReconstruction {
    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        // Initialize graph and in-degree map
        for (List<Integer> seq : sequences) {
            for (int num : seq) {
                graph.putIfAbsent(num, new HashSet<>());
                inDegree.putIfAbsent(num, 0);
            }
        }

        // Build graph and in-degree
        for (List<Integer> seq : sequences) {
            for (int i = 1; i < seq.size(); i++) {
                int u = seq.get(i - 1);
                int v = seq.get(i);
                if (graph.get(u).add(v)) {
                    inDegree.put(v, inDegree.get(v) + 1);
                }
            }
        }

        // If number of nodes != length of nums, return false
        if (nums.length != graph.size()) return false;

        Queue<Integer> q = new LinkedList<>();
        for (int key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                q.offer(key);
            }
        }

        int index = 0;

        while (!q.isEmpty()) {
            if (q.size() > 1) return false; // more than one choice, not unique

            int node = q.poll();
            if (nums[index] != node) return false; // order mismatch
            index++;

            for (int nei : graph.get(node)) {
                inDegree.put(nei, inDegree.get(nei) - 1);
                if (inDegree.get(nei) == 0) {
                    q.offer(nei);
                }
            }
        }

        return index == nums.length;
    }
}

package Graphs.topoSort;

import java.util.*;

/*
🔸 You're Given: A list of words from an alien language.
    These words are sorted lexicographically (i.e., dictionary order) based on the alien language, not English.

    Your task is to:
    ✅ Determine the order of characters in the alien alphabet.

        🔸 Example:
        Input: ["wrt", "wrf", "er", "ett", "rftt"]
        These words are sorted correctly in the alien language.

        From this, you must figure out what character order makes this sorting valid.

        🔸 Output: "wertf"
        This means 'w' comes before 'e', 'e' comes before 'r', and so on.
 */

public class AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        // Initialize the graph
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }

        // Build the graph by comparing adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return "";  // Invalid order (prefix case)
            }

            int len = Math.min(word1.length(), word2.length());
            for (int j = 0; j < len; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                if (c1 != c2) {
                    if (graph.get(c1).add(c2)) {
                        inDegree.put(c2, inDegree.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        // Topological Sort using BFS
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        StringBuilder result = new StringBuilder();

        while (!queue.isEmpty()) {
            char curr = queue.poll();
            result.append(curr);
            for (char neighbor : graph.get(curr)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return result.length() == graph.size() ? result.toString() : "";
    }
}

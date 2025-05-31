package Graphs.DSU;

import java.util.Arrays;

/*
You are given a list of logs that represent friendship events. Each log is a triplet: [timestamp, personA, personB],
indicating that personA and personB became friends at timestamp.
There are n people labeled from 0 to n-1. A person is friends with themselves and anyone in the same connected friendship group.

Your task:
Return the earliest timestamp at which all people became friends (i.e., there is one connected component). If it is not possible, return -1.

✅ Example:
Input:
logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],
        [20190224,2,4],[20190301,0,3],[20190312,1,2]]
n = 6
Output: 20190301
Because at that timestamp, everyone is connected through some path of friendships.
 */
public class EarliestAcq {
    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[0], b[0]));

        UnionFind uf = new UnionFind(n);
        for (int[] log : logs) {
            int time = log[0], a = log[1], b = log[2];
            uf.union(a, b);
            if (uf.getCount() == 1) return time;
        }

        return -1;
    }
}

class UnionFind {
    int[] parent;
    int count;

    UnionFind(int n) {
        parent = new int[n];
        count = n;
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    int find(int x) {
        if (x != parent[x])
            parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    void union(int x, int y) {
        int parX = find(x);
        int parY = find(y);
        if (parX != parY) {
            parent[parX] = parY;
            count--;
        }
    }

    int getCount() {
        return count;
    }
}
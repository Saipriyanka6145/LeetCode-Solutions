import java.util.*;

class Solution {
    private int timer = 0;
    private int[] in, out, depth;
    private int[][] up;
    private int[] bit;
    private int[] charMask;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        int maxLog = 16; // 2^16 > 50,000
        in = new int[n];
        out = new int[n];
        depth = new int[n];
        up = new int[n][maxLog];
        bit = new int[n + 2];
        charMask = new int[n];

        for (int i = 0; i < n; i++) {
            charMask[i] = 1 << (s.charAt(i) - 'a');
        }

        // DFS to set up Euler Tour, LCA Binary Lifting, and Initial Tree Structure
        dfs(0, 0, adj, maxLog);

        // Populate Fenwick Tree with initial node masks
        for (int i = 0; i < n; i++) {
            updateBIT(in[i], charMask[i], n);
            updateBIT(out[i] + 1, charMask[i], n);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);
                int newMask = 1 << (newChar - 'a');
                int diff = charMask[u] ^ newMask;

                if (diff != 0) {
                    charMask[u] = newMask;
                    updateBIT(in[u], diff, n);
                    updateBIT(out[u] + 1, diff, n);
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int lca = getLCA(u, v, maxLog);

                int maskU = queryBIT(in[u]);
                int maskV = queryBIT(in[v]);
                int pathMask = maskU ^ maskV ^ charMask[lca];

                // Check if pathMask has at most 1 bit set
                ans.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return ans;
    }

    private void dfs(int u, int p, List<List<Integer>> adj, int maxLog) {
        in[u] = ++timer;
        up[u][0] = p;

        for (int i = 1; i < maxLog; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }

        for (int v : adj.get(u)) {
            if (v != p) {
                depth[v] = depth[u] + 1;
                dfs(v, u, adj, maxLog);
            }
        }

        out[u] = timer;
    }

    private int getLCA(int u, int v, int maxLog) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        for (int i = maxLog - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }

        if (u == v) return u;

        for (int i = maxLog - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }

        return up[u][0];
    }

    private void updateBIT(int idx, int val, int n) {
        for (; idx <= n; idx += idx & -idx) {
            bit[idx] ^= val;
        }
    }

    private int queryBIT(int idx) {
        int mask = 0;
        for (; idx > 0; idx -= idx & -idx) {
            mask ^= bit[idx];
        }
        return mask;
    }
}
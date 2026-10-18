class Solution {
    long[][] comb;
    long MOD = 1000000007;
    
    public int numOfWays(int[] nums) {
        int n = nums.length;
        comb = new long[n][n];
        for (int i = 0; i < n; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) list.add(num);
        return (int)((dfs(list) - 1 + MOD) % MOD);
    }
    
    private long dfs(List<Integer> list) {
        if (list.size() < 3) return 1;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int root = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < root) left.add(list.get(i));
            else right.add(list.get(i));
        }
        long leftWays = dfs(left);
        long rightWays = dfs(right);
        int m = list.size();
        long ways = (leftWays * rightWays) % MOD;
        return (ways * comb[m - 1][left.size()]) % MOD;
    }
}
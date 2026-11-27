class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 1500;
        int[] freq = new int[maxFactor + 1];
        
        // Count occurrences of each factor
        for (int[] item : items) {
            freq[item[0]]++;
        }
        
        // Precompute how many items have a factor divisible by f
        int[] divisibleCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] == 0) continue;
            for (int multiple = f; multiple <= maxFactor; multiple += f) {
                divisibleCount[f] += freq[multiple];
            }
        }
        
        int[] dp = new int[budget + 1];
        
        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            if (price > budget) continue;
            
            int firstCopyGain = divisibleCount[factor];
            
            // 1. Backward pass: buy the 1st copy of this item (0/1 Knapsack)
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + firstCopyGain);
            }
            
            // 2. Forward pass: buy 2nd, 3rd, ... copies (Unbounded Knapsack)
            for (int b = price; b <= budget; b++) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1);
            }
        }
        
        return dp[budget];
    }
}
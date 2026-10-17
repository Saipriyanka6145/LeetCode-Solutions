class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[] digitSum = new int[n];
        for (int i = 0; i < n; i++) {
            int v = nums[i], s = 0;
            while (v > 0) { s += v % 10; v /= 10; }
            digitSum[i] = s;
        }
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) indices[i] = i;
        Arrays.sort(indices, (i, j) -> {
            if (digitSum[i] != digitSum[j]) return Integer.compare(digitSum[i], digitSum[j]);
            return Integer.compare(nums[i], nums[j]);
        });
        int[] targetPos = new int[n];
        for (int pos = 0; pos < n; pos++) targetPos[indices[pos]] = pos;
        boolean[] visited = new boolean[n];
        int cycles = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cycles++;
                int j = i;
                while (!visited[j]) {
                    visited[j] = true;
                    j = targetPos[j];
                }
            }
        }
        return n - cycles;
    }
}
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Place each valid positive integer x in [1, n] at index x - 1
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int targetIdx = nums[i] - 1;
                int temp = nums[targetIdx];
                nums[targetIdx] = nums[i];
                nums[i] = temp;
            }
        }

        // The first index whose value doesn't match i + 1 is the missing positive
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }
}
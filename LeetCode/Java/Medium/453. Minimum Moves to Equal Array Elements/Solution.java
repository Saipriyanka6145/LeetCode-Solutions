class Solution {
    public int minMoves(int[] nums) {
        int minVal = nums[0];
        long sum = 0;

        for (int num : nums) {
            minVal = Math.min(minVal, num);
            sum += num;
        }

        return (int) (sum - (long) nums.length * minVal);
    }
}
// Approach 2: Case Analysis Equivalent in Java
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        List<Integer> fac = new ArrayList<>();
        for (int x = 1; x * x <= n; x++) {
            if (n % x == 0) {
                fac.add(x);
                if (n / x != x) fac.add(n / x);
            }
        }
        
        int[] target = nums.clone();
        Arrays.sort(target);
        
        int ans = 0;
        for (int d : fac) {
            boolean canSort = true;
            for (int i = 0; i < n; i += d) {
                if (!check(nums, target, i, d)) {
                    canSort = false;
                    break;
                }
            }
            if (canSort) ans += d;
        }
        return ans;
    }
    
    private boolean check(int[] nums, int[] target, int start, int len) {
        for (int i = 0; i < len - 1; i++) {
            if (nums[start + i] > nums[start + i + 1]) {
                for (int j = 0; j < len; j++) {
                    int originalIdx = start + (i + 1 + j) % len;
                    if (nums[originalIdx] != target[start + j]) return false;
                }
                return true;
            }
        }
        for (int i = 0; i < len; i++) {
            if (nums[start + i] != target[start + i]) return false;
        }
        return true;
    }
}
class Solution {
    class BIT {
        int size;
        long[] tree1, tree2;
        public BIT(int n) {
            size = n;
            tree1 = new long[n + 2];
            tree2 = new long[n + 2];
        }
        int lowbit(int index) { return index & -index; }
        void add(int left, int right, int delta) {
            _add(left, delta);
            _add(right + 1, -delta);
        }
        long query(int left, int right) {
            return _query(right) - _query(left - 1);
        }
        void _add(int index, int delta) {
            index += 1;
            int rawIndex = index;
            while (index <= size) {
                tree1[index] += delta;
                tree2[index] += (long)(rawIndex - 1) * delta;
                index += lowbit(index);
            }
        }
        long _query(int index) {
            index += 1;
            int rawIndex = index;
            long res = 0;
            while (index > 0) {
                res += (long)rawIndex * tree1[index] - tree2[index];
                index -= lowbit(index);
            }
            return res;
        }
    }
    public int sumCounts(int[] nums) {
        int n = nums.length;
        BIT bit = new BIT(n);
        Map<Integer, Integer> seen = new HashMap<>();
        long cur = 0, res = 0;
        long MOD = 1000000007;
        for (int i = 0; i < n; i++) {
            int a = nums[i];
            int j = seen.getOrDefault(a, -1) + 1;
            long x_sum = bit.query(j, i);
            long total_increase = (2 * x_sum + (i - j + 1)) % MOD;
            cur = (cur + total_increase) % MOD;
            res = (res + cur) % MOD;
            bit.add(j, i, 1);
            seen.put(a, i);
        }
        return (int)res;
    }
}
class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int cnt1 = 0,cnt2 = 0;
        for(int num : nums){
            if (num < target) cnt1++;
            else if (num == target) cnt2++;
 
        }
        List<Integer> res = new ArrayList<>();
        for(int i=0;i < cnt2;i++){
            res.add(cnt1+i);

        }
        return res;
    }
}
//space=O(log M)
//time=O(n logM)
class Solution {

    public int maxSum(List<Integer> nums, int k) {
        
        int MOD = 1000000007;
        int[] cnt = new int[30];
        for(int num:nums){
            for(int i = 0;i<30;i++){
                if((num & (1<<i)) != 0){
                    cnt[i]++;

                }
            }
        }
        long res = 0;
        for(int i =0;i<k;i++){
            long cur = 0;
            for(int j =29;j>=0;j--){
                if(cnt[j]>0){
                    cur |=(1<<j);
                    cnt[j]--;

                 }
            }
            res = (res+cur*cur)%MOD;
            
        }
        return (int) res;
    }
}
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n =nums1.length;
        int[] diffs = new int[n];
        long totalDiff = 0;
        int maxDiff=0;
        for(int i=0;i<n;i++){
            diffs[i] = Math.abs(nums1[i] - nums2[i]);
            totalDiff +=diffs[i];
            maxDiff =  Math.max(maxDiff,diffs[i]);

        }
        long k = (long) k1+k2;
        if(k>=totalDiff) return 0;
        int[] counts = new int[maxDiff +1];
        for(int d : diffs) counts[d]++;
        for(int i = maxDiff;i > 0 && k > 0;i--){
            if(counts[i]==0) continue;
            long reduce =Math.min((long) counts[i],k);
            counts[i] -=reduce;
            counts[i-1] += reduce;
            k-=reduce;
        }
        long ans=0;
        for(int i=0;i<=maxDiff;i++){
            if(counts[i]>0) ans+= (long) counts[i]*i*i;

        }
        return ans;
        
    }
}
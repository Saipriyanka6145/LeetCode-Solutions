class Solution {
    public int minOperations(int[] nums) {
      int n = nums.length;
      if (n==1) return 0;
      int start = 0;
      for (int i=0;i<n;i++){
        if(nums[i] ==0){
            start = i;
            break;
        }
      }  
      boolean asc = true,desc = true;
      for(int i = 1;i <n;i++){
        int value = nums[(start +i)%n];
        if(value!=i) asc = false;
        if(value !=n-i)desc = false;
        if(!asc && !desc) return-1;

      }
      if(asc) return Math.min(start,n-start+2);
      return Math.min(n-start,start+2);
    }
}
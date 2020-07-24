public class NumArray2 {

    private int[] memo; //memo[i]代表前i个元素的和，memo[0] 代表前0个 元素的和

    public NumArray2(int[] nums) {

        memo = new int[nums.length+1];
        for (int i = 1; i < nums.length+1; i++) {
            memo[i] = memo[i-1] + nums[i-1];
        }

    }

    public int sumRange(int i, int j) {
        return memo[j+1] - memo[i];
    }
}

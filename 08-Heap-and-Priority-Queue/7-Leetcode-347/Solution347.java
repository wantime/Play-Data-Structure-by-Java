
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solution347 {


    public int[] topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for ( int num : nums) {
            if(map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }

        PriorityQueue<Integer> pq =
                new PriorityQueue<>(Comparator.comparingInt(map::get)); //

        PriorityQueue<Integer> pq1 = new PriorityQueue<>(
                (a, b) -> map.get(a) - map.get(b)
        );  //  Lambda 表达式的写法

        PriorityQueue<Integer> pq2 =    //匿名函数
                new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });
        for (int num :
                map.keySet()) {
            if(pq.size() < k)
                pq.add(num);
            else
                if( map.get(num) > map.get(pq.peek()) ){
                    pq.poll();
                    pq.add( num );
                }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res [i] =  pq.poll();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4,1,-1,2,-1,2,3};
        int k = 2;
        int[] res = new Solution347().topKFrequent(arr, k);
        for (int i = 0; i < k; i++) {
            System.out.println(res[i]);
        }
    }
}

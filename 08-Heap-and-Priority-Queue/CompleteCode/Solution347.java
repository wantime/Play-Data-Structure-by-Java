
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solution347 {

    private class Freq implements Comparable<Freq> {
        int e, freq;

        public Freq(int e, int freq){
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another){
            if(this.freq > another.freq)
                return 1;
            else if(this.freq < another.freq)
                return -1;
            else
                return 0;
        }

    }

    public int[] topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for ( int num : nums) {
            if(map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }

        PriorityQueue<Freq> pq = new PriorityQueue<>();

        for (int num :
                map.keySet()) {
            if(pq.size() < k)
                pq.add(new Freq(num, map.get(num)));
            else
                if( map.get(num) > pq.peek().freq ){
                    pq.poll();
                    pq.add( new Freq(num, map.get(num)) );
                }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res [i] =  pq.poll().e;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4,1,-1,2,-1,2,3};
        int k = 2;
        new Solution347().topKFrequent(arr, k);
    }
}

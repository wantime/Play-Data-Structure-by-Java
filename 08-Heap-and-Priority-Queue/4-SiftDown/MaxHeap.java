import sun.font.FontRunIterator;

import java.util.Random;

public class MaxHeap<E extends Comparable> {

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    /**
     * 堆中有多少元素
     * @return
     */
    public int getSize(){
        return data.getSize();
    }

    /**
     * 判断堆是否为空
     */
    public boolean isEmpty(){
        return data.isEmpty();
    }

    /**
     * 以0作为起点
     * @param index
     * @return
     */
    private int parent(int index){
        return (index - 1) / 2;
    }

    /**
     * 获取左孩子索引
     * @param index
     * @return
     */
    private int leftChild(int index){
        return index * 2 + 1;
    }

    /**
     * 获取右孩子索引
     * @param index
     * @return
     */
    private int rightChild(int index){
        return leftChild(index) + 1;
    }

    /**
     * 向堆中添加函数
     * @param e
     */
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 添加元素的辅助函数
     * @param k
     */
    private void siftUp(int k){

        while ( k > 0  && data.get(parent(k)).compareTo(data.get(k)) < 0 ){
            data.swap(parent(k), k);
            k = parent(k);
        }
    }

    public E findMax(){
        if(isEmpty())
            throw new IllegalArgumentException("Heap is Empty !");
        return data.get(0);
    }

    public E extractMax(){

        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int k){

        while ( leftChild(k) < data.getSize()  ){

            int j = leftChild(k);
            if(j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0)
                j = j + 1;
            //  data[j] 是 左右孩子中的最大值
            if(data.get(k).compareTo(data.get(j)) >= 0)
                break;
            data.swap(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++)
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < n; i++) {
            if(arr[i] < arr[i])
                throw new IllegalArgumentException("error ");
        }
        System.out.printf("Test MaxHeap completed.");
    }
}

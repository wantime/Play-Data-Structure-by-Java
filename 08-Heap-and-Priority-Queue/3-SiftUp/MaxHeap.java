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

    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k){

        while ( k > 0  && data.get(parent(k)).compareTo(data.get(k)) < 0 ){
            data.swap(parent(k), k);
            k = parent(k);
        }


    }

}

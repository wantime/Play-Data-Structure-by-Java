import org.omg.CORBA.Object;

public class SegmentTree<E>{

    private E[] data;
    private E[] tree;
    private Merger<E> merger;
    /**
     * 构造函数
     * @param arr
     */
    public SegmentTree(E[] arr, Merger<E> merger){

        this.merger =merger;

        data =(E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);

    }

    /**
     * 在treeIndex的位置创建表示区间[l...r]的线段树
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex, int l, int r){

        if(l == r) {
            tree[l] = data[l];
            return;
        }

        int leftChildIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftChildIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid+1, r);

        tree[treeIndex] =
                merger.merge(tree[leftChildIndex], tree[rightTreeIndex]);
    }


    /**
     * 获取元素个数
     * @return
     */
    public int getSize(){
        return data.length;
    }

    /**
     * 获取指定位置的元素
     * @param index
     * @return
     */
    public E get(int index){
        if(index <0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    /**
     * 左孩子索引
     * @param index
     * @return
     */
    private int leftChild(int index){
        return 2*index + 1;
    }

    /**
     * 右孩子索引
     * @param index
     * @return
     */
    private int rightChild(int index){
        return 2*index + 2;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if(tree[i] == null)
                res.append("NULL");
            else
                res.append(tree[i]);

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    public static void main(String[] args) {
        //test
        Integer[] nums = {1,2,3,4,5,6};
        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
                (a, b) ->  a + b);
    }
}

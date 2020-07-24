import com.sun.org.apache.bcel.internal.generic.ILOAD;
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
     * 查询区间[queryL...queryR]的值
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL, int queryR){

        if(queryL < 0 || queryL >= data.length ||
                queryR < queryL || queryR >= data.length)
            throw new IllegalArgumentException("Index is illegal.");

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在以treeIndex为根的线段树中[l...r]范围里，搜索区间[queryL...queryR]的值
     * @param treeIndex
     * @param l
     * @param r
     * @param queryL
     * @param queryR
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR){

        if (l == queryL && r == queryR)
            return tree[treeIndex];

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if(queryL >= mid + 1)
            return query(rightTreeIndex, mid+1, r, queryL, queryR);
        else if(queryR <= mid)
            return query(leftTreeIndex, l, mid, queryL, queryR);

        E leftResult = query(leftTreeIndex, l, mid, queryL, queryR);
        E rightResult = query(rightTreeIndex, mid+1, r, queryL, queryR);
        return merger.merge(leftResult, rightResult);
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
    public void set(int index, E e){

        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");

        data[index] = e;
        set(0,  0, data.length-1, index, e);
    }

    private void set(int treeIndex,  int l, int r, int index, E e){

        if( l == r ){
            tree[treeIndex] = e;
            return;
        }

        int leftChildIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        if(index <= mid)
            set(leftChildIndex, l, mid, index, e);
        else //if(index > mid)
            set(rightTreeIndex, mid+1, r, index, e);

        tree[treeIndex] = merger.merge(tree[leftChildIndex],
                tree[rightTreeIndex]);

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

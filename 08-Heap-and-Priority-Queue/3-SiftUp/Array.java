public class Array<E> {

    //  把java的数组包装成为自己的类，实现了增删查改等常规方法，并且为队列和栈提供底层数据结构
    private E[] data;   //  存储数据的数组
    private int size;   //  数据的大小

    /**
     * 构造函数，传入数组的容量capacity构造Array
     * @param capacity 容量
     */
    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    /**
     * 无参数的构造函数，默认数组的容量capacity=10
     */
    public Array(){
        this(10);
    }

    /**
     * 获取数组中的元素个数
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 获取数组的容量
     * @return
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * 返回数组是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 向指定位置添加元素
     */
    public void add(int index, E e){

        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

        if(size == data.length)
            resize(2 * data.length);

        for(int i = size - 1; i >= index ; i --)
            data[i + 1] = data[i];

        data[index] = e;

        size ++;
    }

    /**
     * 向所有元素后添加一个新元素
     * @param e 添加的元素
     */
    public void addLast(E e){
        add(size, e);
    }

    /**
     * 在所有元素前面添加一个新元素
     * @param e 添加的元素
     */
    public void addFirst(E e){
        add(0, e);
    }

    /**
     * 获取index索引位置的元素
     * @param index
     * @return
     */
    public E get(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    /**
     * 获取数组的最后一个元素
     * @return
     */
    public E getLast(){
        return get(size - 1);
    }

    /**
     * 获取数组的第一个元素
     * @return
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * 修改index索引位置的元素为e
     * @param index
     * @param e
     */
    public void set(int index, E e){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }

    /**
     * 查找数组中是否有元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
        for(int i = 0; i < size; i ++){
            if(data[i].equals(e))
                return true;
        }
        return false;
    }

    /**
     * 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
     * @param e
     * @return
     */
    public int find(E e){
        for(int i = 0; i < size; i ++){
            if(data[i].equals(e))
                return i;
        }
        return -1;
    }

    /**
     * 从数组中删除index位置的元素，返回删除的元素
     * @param index
     * @return
     */
    public E remove(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        E ret = data[index];
        for(int i = index + 1; i < size; i ++)
            data[i - 1] = data[i];

        size --;
        data[size] = null; // loitering objects != memory leak

        if(size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
        return ret;
    }

    /**
     * 从数组中删除第一个元素， 返回删除的元素
     * @return
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 数组中删除最后一个元素，返回删除的元素
     * @return
     */
    public E removeLast(){
        return remove(size - 1);
    }

    /**
     * 从数组中删除元素e
     * @param e
     */
    public void removeElement(E e){
        int index = find(e);
        if(index != -1)
            remove(index);
    }

    /**
     * 从数组中删除所有的元素e
     * @param e
     */
    public void removeAllElement(E e){
        int index = find(e);
        while(index != -1){
            remove(index);
            index = find(e);
        }
    }

    /**
     * 实现动态数组的辅助函数
     * @param newCapacity
     */
    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for(int i = 0; i < size; i ++)
            newData[i] = data[i];
        data = newData;
    }

    /**
     * 交换i位置与j位置上的元素
     * @param i
     * @param j
     */
    public void swap(int i, int j){

        if(i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException(" Illegal indexes !");
        E tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append((String.format("Array: size = %d, capacity = %d\n", size, data.length)));
        res.append('[');
        for(int i = 0; i < size; i ++){
            res.append(data[i]);
            if(i != size -1)
                res.append(",");
        }
        res.append(']');
        return res.toString();
    }


}

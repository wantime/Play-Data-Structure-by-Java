
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    /**
     * 循环队列的构造函数
     * @param capacity
     */
    public LoopQueue(int capacity){
        data = (E[])new Object[capacity + 1];   //  申明一个支持泛型的数组
        front = 0;  //  头指针
        tail = 0;   //  尾指针
        size = 0;   //  元素个数
    }

    /**
     * 无参数构造函数，直接调用有参数构造函数，传入容量为10
     */
    public LoopQueue(){
        this(10);
    }

    /**
     * 获得队列的容量
     * @return
     */
    public int getCapacity(){
        return data.length - 1;
    }

    /**
     * 判断队列是否为空
     * @return
     */
    @Override
    public boolean isEmpty(){
        return front == tail;
    }

    /**
     * 获取队列中的元素个数
     * @return
     */
    @Override
    public int getSize(){
        return size;
    }

    /**
     * 入队操作
     * @param e
     */
    @Override
    public void enqueue(E e){

        if((tail + 1) % data.length == front)
            resize(getCapacity() * 2);

        data[tail] = e;
        //  当tail指向了末尾时，通过（tail + 1） % data
        //  .length，可以tail指向数组的前面（由于出队操作空出来的位置，以避免空间的浪费）
        tail = (tail + 1) % data.length;
        size ++;
    }

    /**
     * 出队操作
     * @return
     */
    @Override
    public E dequeue(){

        if(isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        E ret = data[front];
        data[front] = null;
        //  这里与入队时一样，可以使指针从数组的末尾跳转回数组的开始位置，避免了位置的浪费
        front = (front + 1) % data.length;
        size --;
        //  避免复杂度振荡
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
        return ret;
    }

    /**
     * 查看队首的元素
     * @return
     */
    @Override
    public E getFront(){
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return data[front];
    }

    /**
     * 实现动态数组的辅助函数
     * @param newCapacity
     */
    private void resize(int newCapacity){

        E[] newData = (E[])new Object[newCapacity + 1];
        for(int i = 0; i < size; i ++)
            newData[i] = data[(i + front) % data.length];

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append((String.format("Queue: size = %d, capacity = %d\n", size, getCapacity())));
        res.append("front [");
        for(int i = front; i != tail; i = (i + 1) % data.length){
            res.append(data[i]);
            if((i + 1) % data.length != tail)
                res.append(",");
        }
        res.append("] tail");
        return res.toString();
    }
    public static void main(String[] args){
        // test the LoopQueue
        LoopQueue<Integer> queue = new LoopQueue<>();
        for(int i = 0; i < 10; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}

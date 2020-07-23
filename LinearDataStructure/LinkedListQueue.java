public class LinkedListQueue<E> implements Queue<E> {

    private LinkedList<E> list;

    public LinkedListQueue(){
        list = new LinkedList<>();
    }
    /**
     * 获取队列中元素的个数
     * @return
     */
    @Override
    public int getSize(){
        return list.getSize();
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     * 入队操作
     * @param e
     */
    public void enqueue(E e){
        list.addLast(e);
    }

    /**
     * 出队操作
     * @return
     */
    public E dequeue(){
        return list.removeFirst();
    }

    /**
     * 查看队首元素
     * @return
     */
    public E getFront(){
        return list.getFirst();
    }

}

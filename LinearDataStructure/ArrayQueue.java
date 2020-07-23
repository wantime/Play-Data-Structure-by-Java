public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(int capacity){
        array = new Array<>(capacity);
    }

    public ArrayQueue(){
        array = new Array<>();
    }

    @Override
    public boolean isEmpty(){
        return array.isEmpty();
    }

    @Override
    public int getSize(){
        return array.getSize();
    }

    /**
     * 队列的入队操作就是在数据的末尾添加一个元素
     * @param e
     */
    @Override
    public void enqueue(E e){
        array.addLast(e);
    }

    /**
     * 队列的出队操作就是删除并返回数组的第一个位置的元素
     * @return
     */
    @Override
    public E dequeue(){
        return array.removeFirst();
    }

    @Override
    public E getFront(){
        return array.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for(int i = 0; i < array.getSize(); i ++){
            res.append(array.get(i));
            if(i != array.getSize() - 1)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args){
        // test ArrayQueue
        ArrayQueue<Integer> queue = new ArrayQueue<>();
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

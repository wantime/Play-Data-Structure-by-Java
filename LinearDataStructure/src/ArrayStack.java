public class ArrayStack<E> implements  Stack<E> {

    Array<E> array;

    /**
     * 有参构造函数，传入栈的初始容量
     * @param capacity
     */
    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }

    /**
     * 无参构造函数
     */
    public ArrayStack(){
        array = new Array<>();
    }

    /**
     * 获取栈中元素个数
     * @return
     */
    @Override
    public int getSize(){
        return array.getSize();
    }

    /**
     * 栈是否为空
     * @return
     */
    @Override
    public boolean isEmpty(){
        return array.isEmpty();
    }

    /**
     * 栈的容量
     * @return
     */
    public int getCapacity(){
        return array.getCapacity();
    }

    /**
     * 向栈中加入元素
     * @param e
     */
    @Override
    public void push(E e){
        array.addLast(e);
    }

    /**
     * 取出栈顶元素
     * @return
     */
    @Override
    public E pop(){
        return array.removeLast();
    }

    /**
     * 查看栈顶元素
     * @return
     */
    @Override
    public E peek(){
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for(int i = 0; i < array.getSize(); i ++){
            res.append(array.get(i));
            if(i != array.getSize() - 1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }

}

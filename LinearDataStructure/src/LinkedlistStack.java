public class LinkedlistStack<E> implements Stack<E> {

    private LinkedList<E> list;

    /**
     * 无参构造函数，底层用链表实现，因此不需要指定容量
     */
    public LinkedlistStack(){
        list = new LinkedList<>();
    }

    /**
     * 获取栈中元素的个数
     * @return
     */
    @Override
    public int getSize(){
        return list.getSize();
    }

    /**
     * 判断栈是否为空
     * @return
     */
    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     * 向栈中添加元素
     * @param e
     */
    @Override
    public void push(E e){
        list.addFirst(e);
    }

    /**
     * 取出栈顶元素
     * @return
     */
    @Override
    public E pop(){
        return list.removeFirst();
    }

    /**
     * 查看栈顶元素
     * @return
     */
    @Override
    public E peek(){
        return list.getFirst();
    }

    /**
     * 重载toString方法
     * @return
     */
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }
    public static void main(String[] args) {
        // Test Linkedlist
        LinkedlistStack<Integer> ls = new LinkedlistStack();

        for(int i = 0; i < 5; i ++){
            ls.push(i);
            System.out.println(ls);
        }
        ls.pop();
        System.out.println(ls);
    }
}

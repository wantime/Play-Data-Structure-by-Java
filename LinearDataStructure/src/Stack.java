public interface Stack<E> {
    /**
     * 获取栈中元素个数
     * @return
     */
    int getSize();

    /**
     * 判断栈是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 向栈中添加元素
     * @param e
     */
    void push(E e);

    /**
     * 取出栈顶的元素
     * @return
     */
    E pop();

    /**
     * 查看栈顶的元素
     * @return
     */
    E peek();
}

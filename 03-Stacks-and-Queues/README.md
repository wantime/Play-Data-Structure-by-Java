## 各种数据结构的java实现 
#### 03-栈与队列
	（实现DFS与BFS的辅助数据结构），以02中的数组作为底层结构，同时实现了循环队列
###### 函数：
Stack:
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
Queue:
	/**
     * 获取队列中元素的个数
     * @return
     */
    int getSize();
    /**
     * 判断队列是否为空
     * @return
     */
    boolean isEmpty();
    /**
     * 入队操作
     * @param e
     */
    void enqueue(E e);
    /**
     * 出队操作
     * @return
     */
    E dequeue();
    /**
     * 查看队首元素
     * @return
     */
    E getFront();
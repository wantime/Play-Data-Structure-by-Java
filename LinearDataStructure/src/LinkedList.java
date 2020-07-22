public class LinkedList<E> {

    //  链表由多个节点组成，每个节点由值与指向下一个节点的next构成
    private class Node{
        public E e;
        public Node next;

        /**
         * 节点的构造函数，传入当前节点的值与下一个节点
         * @param e
         * @param next
         */
        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        /**
         * 节点的构造函数，只传入当前节点
         * @param e
         */
        public Node(E e){
            this(e,null);
        }

        /**
         * 无参数的构造函数
         */
        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }


    private Node dummyHead; //  虚拟头结点

    int size;   //  链表中元素的个数

    /**
     * 链表的构造函数，虚拟头结点指向链表的第一个元素
     */
    public LinkedList(){
        dummyHead = new Node(null,null);
        size = 0;
    }

    /**
     * 获取链表个数
     * @return个数
     */
    public int getSize(){
        return size;
    }

    /**
     * 返回链表是否为空
     * @return
     */
    public boolean isEmpty(){
        return size ==0;
    }

    /**
     *  向链表中指定位置插入元素
     * @param index 位置索引
     * @param e 元素
     */
    public void add(int index, E e){

        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
        //  使用prev指针开始，找到需要插入节点的位置
        Node prev = dummyHead;
        for(int i = 0; i < index; i ++)
            prev = prev.next;

        prev.next = new Node(e, prev.next);
        size ++;
        }

    /**
     * 在链表头添加新元素
     * @param e
     */
    public void addFirst(E e){
        add(0,e);
    }
    /**
     * 向链表尾部添加元素
     * @param e
     */
    public void addLast(E e){
        add(size, e);
    }

    /**
     * 获取指定位置的链表元素
     * @param index
     * @return
     */
    public E get(int index){

        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i ++)
            cur = cur.next;
        return cur.e;
    }

    /**
     * 获取第一个元素
     * @return
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * 获取最后一个元素
     * @return
     */
    public E getLast(){
        return get(size - 1);
    }
    /**
     * 修改链表的第index(0-based)个元素为e
     * 非常规操作，for practice
     */
    public void set(int index, E e){

        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");
        Node cur = dummyHead.next;
        for(int i = 0; i < index; i ++)
            cur = cur.next;
        cur.e = e;
    }

    /**
     * 删除指定位置的元素
     * @param index
     * @return
     */
    public E remove(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Illegal index.");
        Node prev = dummyHead;
        for(int i = 0; i < index; i ++)
            prev = prev.next;
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;

        return retNode.e;
    }

    /**
     * 删除第一个元素，并返回这个元素
     * @return
     */
    public E removeFirst(){
        return remove(0);
    }
    /**
     * 删除最后一个元素，并返回这个元素
     * @return
     */
    public E removeLast(){
        return remove(size - 1);
    }
    /**
     * 查找链表中是否有元素e
     */
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return  false;
    }



    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while (cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }

}

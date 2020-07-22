
public class LinkedList<K, V> {

    /**
     * 链表中的节点，为了支持Map数据结构，需要键值对进行配合
     */
    private class Node{
        public K key;
        public V value;
        public Node next;

        /**
         * 有参构造函数
         * @param key
         * @param value
         * @param next
         */
        public Node(K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value){
            this(key, value, null);
        }
        public Node(){
            this(null, null);
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("{" + key.toString() + ":" + value.toString() + "}");
            return res.toString();
        }
    }

    private Node dummyHead;     //  虚拟头结点
    private int size;           //  记录节点个数

    /**
     * 构造函数
     */
    public LinkedList(){
        dummyHead = new Node();
        size = 0;
    }

    /**
     * 获取节点个数
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 判断链表是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 添加操作
     * @param key
     * @param value
     */
    public void add(K key, V value){
        Node prev = dummyHead;
        while (prev.next != null)
            prev = prev.next;
        prev.next = new Node(key,value);
        size ++;
    }

    /**
     * 判断是否包含某元素
     * @param key
     * @return
     */
    public boolean contains(K key){
        Node cur = dummyHead.next;
        while (cur != null) {
            if(cur.key.equals(key))
                return true;
            cur = cur.next;
        }
        return false;
    }

    /**
     * 修改某个已存在元素的值
     * @param key
     * @param newValue
     */
    public void set(K key, V newValue){
        Node cur = dummyHead.next;
        while (cur != null){
            if(cur.key.equals(key)) {
                cur.value = newValue;
                return;
            }
            cur = cur.next;
        }
        throw new IllegalArgumentException("there is no key in the map !");
    }

    /**
     * 获取某个键对应的值，如果不存在则返回空
     * @param key
     * @return
     */
    public V get(K key){
        Node cur = dummyHead.next;
        while (cur != null){
            if(cur.key.equals(key))
                return cur.value;
            cur = cur.next;
        }
        return null;
    }

    /**
     * 删除某个指定键值对
     * @param key
     * @return
     */
    public V removeElement(K key){
        Node prev = dummyHead;
        while (prev.next != null){
            if(prev.next.key.equals(key))
                break;
            prev = prev.next;
        }
        if(prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.value;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while (cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer, Integer> list = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i, i+1);
        }
        System.out.println(list);
        list.set(0, 59);
        System.out.println(list);
    }
}

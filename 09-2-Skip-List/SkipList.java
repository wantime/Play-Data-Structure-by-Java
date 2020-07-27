
import java.security.PublicKey;
import java.util.Random;

public class SkipList<E extends Comparable<E>> {

    //  节点类，跟链表差不多
    private class Node<E extends Comparable<E>>{
        public E e;     //  跳表需要可比较的值类型
        public Node next;   //  指向右边节点
        public Node down;   //  指向下一层的对应节点

        /**
         * 带参数的构造函数，其实没用上
         * @param e
         * @param next
         * @param down
         */
        public Node(E e, Node next, Node down){
            this.e = e;
            this.next = next;
            this.down = down;
        }

        /**
         * 构造函数2，少了一个参数
         * @param e
         * @param next
         */
        public Node(E e, Node next){
            this(e, next, null);
        }

        /**
         * 构造函数3，
         * @param e
         */
        public Node(E e){
            this(e, null, null);
        }
        //构造函数4
        public Node(){
            this(null, null, null);
        }
    }

    private int size;   //  记录跳表中元素的个数
    private  int maxLevel;  //  最大层，用户可指定
    private Node<E> dummyHead;  //  跳表入口，左上角的虚拟头结点

    /**
     * 链表的构造函数
     * @param maxLevel
     */
    public SkipList(int maxLevel){
        dummyHead = new Node<>();
        this.maxLevel = maxLevel;
        Node cur = dummyHead;

        //  这里是为了让虚拟头结点的down指向下一层的虚拟头结点，从而保证了整体逻辑的一致性
        for (int i = 1; i < maxLevel; i++) {
            cur.down = new Node();
            cur = cur.down;
        }
    }
    public SkipList(){
        this(15);
    }

    /**
     * 把一个元素插入一个有序的链表中，插入后链表的元素依然有序
     * @param e
     */
    public void add(E e){
        //  获取一个随机层数，这个应该是跳表得以成立的关键了。为什么这么说
        //  看了好多资料，理想情况是第0层的元素为n个，那么第1层应该是n/k个，假设k为2，就每隔一个插入一个
        //  那么新加入的一个元素，就会打破这种漂亮的形状
        //  所以之间在最开始，加入第一个元素的时候就随机选择一个层，这一层往下都有这个元素，从统计结果上，就能达到预想的性能
        int randomLevel = getRomdom();
        //  调用私有的方法，因为是从最上层开始的，所以需要传入maxLevel
        //  randomLevel已经说过了
        add(dummyHead, e, maxLevel, randomLevel);
        size ++;
    }

    /**
     * 当满足条件时，向以node为虚拟节点的链表中插入元素，
     * @param node
     * @param e
     * @param curlevel
     * @param randomLevel
     * @return
     */
    private Node add(Node node, E e, int curlevel ,int randomLevel){

        Node pre = node;

        while (pre.next != null && pre.next.e.compareTo(e) < 0 ){
            //  找到一个位置，它的下一个pre.next的值比给的元素大
            //  而当前元素的值比给定的元素小，插入这个值
            //  异常处理，当pre.next == null时，pre指向了链表的尾部
            pre = pre.next;
        }
        // 此时已经找到了pre这个地方,然后判断此时是否到达了我们需要的这一层，也就是curlevel < randomLevel
        //  只有小于和等于，才进行添加节点的操作，否则直接传入pre节点，进入下一层

        if( curlevel > randomLevel ) {
            //  当前层已经完成，需要curLevel --
            add(pre.down, e, curlevel - 1, randomLevel);
            return null;
        }
        else {
            //  当来到这里时，说明已经到了可以加入元素的层了
            //  cur 是需要插入的那个元素
            Node cur = new Node(e);
            //  nex为空也可以处理
            Node nex = pre.next;
            pre.next = cur;
            cur.next = nex;
            //  这里判断curlevel来到了第几层
            if (curlevel > 1)
                //  当前层已经完成，需要curLevel --
                //  这里比较重要，因为需要连接上一层的cur与下一层的cur
                cur.down = add(pre.down, e, curlevel - 1, randomLevel);

            return cur;
        }
    }

    /**
     * 查询跳表中是否包含某个元素
     * @param e
     * @return  查找看起来是最简单的，从最开始（左上角）进行查找，遇到下一个元素比它大就去下一层，找到就返回true，到终止条件都没找着就返回false
     */
    public boolean contains(E e){
        Node pre = dummyHead;
        while (pre != null){

            while (pre.next != null && pre.next.e.compareTo(e) < 0)
                pre = pre.next;

            if(pre.next == null || pre.next.e.compareTo(e) > 0)
                pre = pre.down;
            else if(pre.next.e.compareTo(e) == 0)
                return true;

        }

        return false;
    }

    /**
     * 删除一个元素，如果存在并且删除就返回true，否则返回false
     * @param e
     * @return
     */
    public boolean remove(E e){
        boolean res = false;
        if(contains(e))
            res = true;
        remove(dummyHead, e);
        size --;
        return res;
    }

    /**
     * 删除也是先查找，找到之后先去它的下一层继续找。用了递归，等递归的最低层找到之后，开始进行删除操作，底层删完返回上一层，继续删除操作
     * @param node
     * @param e
     */
    private void remove(Node node, E e){

        if(node == null)
            return;

        Node pre = node;
        while (pre.next != null && pre.next.e.compareTo(e) < 0){
            pre = pre.next;
        }
        //  找到这个节点之后，下去它下面那一层
        remove(pre.down, e);
        //  等下面那一层删除完毕后，再进行这一层的删除工作
        if(pre.next != null && pre.next.e == e){
            Node delNode = pre.next;
            pre.next = delNode.next;
            delNode.next = null;
        }

    }


    /**
     * 获得一个随机整数，用于新添加一个元素时，把它插入这一层
     * @return  返回值在[0...maxLevel]之间，0-31
     */
    private int getRomdom(){
        Random random = new Random();
        return random.nextInt(maxLevel) + 1;
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        Node head = dummyHead;
        while (head != null){
            Node cur = head.next;
            while (cur != null){
                res.append(cur.e);
                res.append("->");
                cur = cur.next;
            }
            res.append("NULL");
            head = head.down;
            res.append("\n");
        }
        return res.toString();
    }


    public static void main(String[] args) {

        SkipList<Integer> skipList = new SkipList<>(1);

        for (int i = 0; i < 5; i++) {
            skipList.add(i);
        }
        System.out.println(skipList.toString());
        if(skipList.remove(0))
            System.out.println(skipList.toString());

    }
}

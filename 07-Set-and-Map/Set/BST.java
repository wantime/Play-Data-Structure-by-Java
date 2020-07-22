import java.util.LinkedList;
import java.util.Queue;

public class BST<E extends Comparable<E>> {

    /**
     * 内部的树节点
     */
    private class Node{
        E e;    //  存储节点的值
        Node left, right;  //  左,右孩子
        /**
         * 构造函数
         */
        public Node(E e){
            this.e = e;
            left = null;
            right  = null;
        }

    }

    private Node root;  //  根节点
    private int size;   //  节点个数

    public BST(){
        root = null;
        size = 0;
    }
    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e){
        root = add(root, e);
    }

    /**
     * 向二叉搜索树中添加元素的辅助函数
     * @param node
     * @param e
     * @return 该部分需要返回值的为了防止在node = new Node后，结束函数，node被销毁，没有传递节点
     */
    private Node add(Node node, E e){
        if(node == null) {
            size ++;
            return new Node(e);
        }
        else if(e.compareTo(node.e) > 0)
            node.right = add(node.right, e);
        else
            node.left = add(node.left, e);
        return node;
    }

    /**
     * 查询二叉树中是否包含某个元素
     * @param e
     * @return
     */
    public boolean contains(E e){
        return contains(root, e);
    }

    /**
     * 查询函数的辅助函数
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e){
        if(node == null)
            return false;

        if(node.e == e)
            return true;
        else if(e.compareTo(node.e) > 0)
            return contains(node.right, e);
        else
            return contains(node.left, e);
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }
    private void preOrder(Node node){
        if(node == null)
            return;
        // do what you want to the current node
        System.out.printf(node.e.toString() + "-->");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     */
    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node node){
        if(node == null)
            return;
        inOrder(node.left);
        // do what you want to the current node
        System.out.printf(node.e.toString() + "-->");
        inOrder(node.right);
    }

    /**
     * 后序遍历
     */
    public void pastOrder(){
        pastOrder(root);
    }
    private void pastOrder(Node node){
        if(node == null)
            return;
        pastOrder(node.left);
        // do what you want to the current node
        System.out.printf(node.e.toString() + "-->");
        pastOrder(node.right);
    }

    /**
     * 二叉树的层序遍历
     */
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node cur = queue.poll();

            //  do what you want to the current node
            System.out.printf(cur.e.toString() + "-->");

            if(cur.left != null)
                queue.add(cur.left);
            if(cur.right != null)
                queue.add(cur.right);
        }
    }

    /**
     * 删除最小值的非递归写法
     * @return
     */
    public E removeMinNR(){
        Node cur = root;
        while (cur.left.left != null)
            cur = cur.left;
        E e = cur.left.e;
        cur.left = cur.left.right;
        size --;
        return e;
    }

    /**
     * 删除最小值的递归写法
     * @return
     */
    public E removeMin(){
        E ret = findMin();
        root = removeMin(root);
        return ret;
    }

    /**
     * 去除最小值的递归写法
     * @param node
     * @return
     */
    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 找到最小值
     * @return
     */
    public E findMin(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty !");
        return findMin(root).e;
    }
    private Node findMin(Node node){

        if(node.left == null)
            return node;
        return findMin(node.left);
    }

    /**
     * 去除树中拥有最大值的节点，并返回它的值
     * @return
     */
    public E removeMax(){
        E ret = findMax();
        root = removeMax(root);
        return ret;
    }
    private Node removeMax(Node node){

        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }
    /**
     * 找到最大值
     */
    public E findMax(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty !");
        return findMax(root).e;
    }

    /**
     * 找到最大值的辅助函数
     * @param node
     * @return
     */
    private Node findMax(Node node){
        if(node.right == null)
            return node;
        return findMax(node.right);
    }

    /**
     * 删除某个指定元素
     * @param e
     */
    public void remove(E e){
        root = remove(root, e);
    }

    /**
     * 在BST中删除元素后，必须进行平衡操作，下面是辅助函数，被删除元素可以分为同时有左右孩子，只有一边有孩子以及无孩子三种情况
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e){

        if(node == null)
            return null;

        if(e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        }

        else if(e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        }
        else {  //  node.e == e;
            if(node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            else if(node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            // node.left != null && node.right != null
            Node successor = findMin(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
    }


}

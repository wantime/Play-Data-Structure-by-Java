import javax.print.DocFlavor;
import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;      //  记录每一个节点的高度值

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    //  判断是否是一棵二分搜索树
    public boolean isBST(){

        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 0; i < keys.size()-1; i++) {
            if (keys.get(i).compareTo(keys.get(i+1)) > 0)
                return false;
        }
        return true;

    }
    // BST的中序遍历结果应当是有序的
    private void inOrder(Node node, ArrayList<K> keys){

        if(node == null)
            return;
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    /**
     * 判断一棵二叉树是否是平衡二叉树
     * @return
     */
    public boolean isBalanced(){
        return isBalanced(root);
    }
    private boolean isBalanced(Node node){

        if(node == null)
            return true;

        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);

    }


    //  获取给定节点node的高度
    private int getHeight(Node node){
        if (node == null)
            return 0;
        else
            return node.height;
    }

    private int getBalanceFactor(Node node){
        if( node == null )
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }
    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotation(Node y){

        Node x = y.left;
        Node T3 = x.right;

        //  向右旋转过程
        x.right = y;
        y.left = T3;

        //  更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }
    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotation(Node y){

        Node x = y.right;
        Node T3 = x.left;

        //  向左旋转过程
        x.left = y;
        y.right = T3;

        //  更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }


    // 向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value){
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        //  在以当前node为根添加了新节点后，需要维护当前节点的高度值
        node.height = 1 + Math.max(getHeight(node.left),
                getHeight(node.right));

        //  计算平衡因子
        int balanceFactor = getBalanceFactor(node);
//        if(Math.abs(balanceFactor) > 1)
//            System.out.println("unBalanced :" + balanceFactor);

        //  平衡维护判断的标准，首先递归到底层，获取节点的平衡因子，如果平衡因子>1，说明左孩子比右孩子高
        //  去看左孩子的平衡因子，如果左孩子的平衡因子是>=0,说明左孩子的左孩子高度比右孩子高，新添加的节点应该在左孩子
        //  否则左孩子的平衡因子<0，说明左孩子的高度比右孩子低，那么新添加的节点应该在右孩子
        //  对于当前节点平衡因子<-1，判断过程一致
        //  向左倾斜就右旋转    LL

        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotation(node);
        //  向右倾斜就左旋转    RR
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotation(node);
        //   LR
        // 对节点x进行向左旋转操作，然后对节点y向右旋转
        //        y                              y                         z
        //       / \                           /   \                    /    \
        //      x   T4     向左旋转 (x)        z     T4    向右旋转(y)   x      y
        //     / \       - - - - - - - ->   /  \      - - - - - ->   /  \   /  \
        //   T1   z                        x   T3                   T1  T2 T3  T4
        //       / \                     /  \
        //     T2  T3                   T1  T2
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }
        //   RL
        // 先进行右旋转，再进行左旋转,与LR完全对称
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }
        return node;
    }




    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){

        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    // 从二分搜索树中删除键为key的节点
    public V remove(K key){

        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){

        if( node == null )
            return null;
        Node retNode;
        if( key.compareTo(node.key) < 0 ){
            node.left = remove(node.left , key);
            retNode = node;
        }
        else if(key.compareTo(node.key) > 0 ){
            node.right = remove(node.right, key);
            retNode = node;
        }
        else{   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            }

            // 待删除节点右子树为空的情况
            else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            }

            else{// 待删除节点左右子树均不为空的情况

                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }

        if(retNode == null)
            return null;
        //  在以当前node为根添加了新节点后，需要维护当前节点的高度值
        retNode.height = 1 + Math.max(getHeight(retNode.left),
                getHeight(retNode.right));

        //  计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        //LL
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotation(retNode);
        //R
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            return leftRotation(retNode);
        //   LR
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            retNode.left = leftRotation(retNode.left);
            return rightRotation(retNode);
        }
        //   RL
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
            retNode.right = rightRotation(retNode.right);
            return leftRotation(retNode);
        }
        return retNode;

    }

}

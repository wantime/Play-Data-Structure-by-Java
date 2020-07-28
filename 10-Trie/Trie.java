import java.util.Map;
import java.util.TreeMap;

public class Trie {

    private class Node{
        private boolean isWord;
        TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }
        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    /**
     * 向Trie中添加一个新的单词
     * @param word
     */
    public void add(String word){

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        if(!cur.isWord) {
            cur.isWord = true;
            size ++;
        }
    }

    /**
     * 添加的递归入口
     * @param word
     */
    private void addR(String word){

        addR(word, 0, root);
    }

    /**
     * 添加的递归写法
     * @param word
     * @param index
     * @param node
     */
    private void addR(String word, int index, Node node) {

        if(index == word.length()){
            if(!node.isWord) {
                node.isWord = true;
                size++;
            }
            return;
        }

        char c = word.charAt(index);
        if (node.next.get(c) == null)
            node.next.put(c, new Node());

        addR(word, index+1, node.next.get(c));
    }

    /**
     * 查询一个单词
     * @param word
     * @return
     */
    public boolean contains(String word){

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /**
     * 查询一个单词的递归写法
     * @param word
     * @return
     */
    private boolean containsR(String word){
        return containsR(word, 0, root);
    }
    private boolean containsR(String word, int index, Node node){
        if(index == word.length())
            return node.isWord;

        char c = word.charAt(index);
        if(node.next.get(c) == null)
            return false;
        return containsR(word, index+1, node.next.get(c));

    }

    /**
     * 查询是否在Trie中有单词以prefix为前缀
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix){

        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null)
                return false;
        }
        return true;
    }

    /**
     * 支持正则表达式的查询
     * @param word
     * @return
     */
    public boolean search(String word){

        return match(root, word, 0);
    }

    /**
     * 辅助函数
     * @param node
     * @param word
     * @param index
     * @return
     */
    private boolean match(Node node, String word, int index){

        if(index == word.length())
            return true;

        char c = word.charAt(index);
        if(c != '.'){
            if(node.next.get(c) == null)
                return false;
            return match(node.next.get(c), word, index+1);
        }
        else {
            for (char nextChar :
                    node.next.keySet()) {
                if(match(node.next.get(nextChar), word, index+1))
                    return true;
            }
        }
        return false;
    }
}


public class LinkedList<E> {

    private class Node{
        public E e;
        public Node next;

        public Node(E e){
            this.e = e;
            this.next = null;
        }
        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }
        public Node(){
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList(){
        dummyHead = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e){
        Node prev = dummyHead;
        while (prev.next != null)
            prev = prev.next;
        prev.next = new Node(e);
        size ++;
    }

    public boolean contains(E e){
        Node cur = dummyHead.next;
        while (cur != null) {
            if(cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    public void removeElement(E e){
        Node prev = dummyHead;
        while (prev.next != null){
            if(prev.next.e.equals(e))
                break;
            prev = prev.next;
        }
        if(prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
        }
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
}

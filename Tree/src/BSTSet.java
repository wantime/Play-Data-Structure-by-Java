import javax.swing.plaf.PanelUI;

public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet(){
        bst = new BST<>();
    }

    @Override
    public int getSize(){
        return bst.getSize();
    }

    @Override
    public boolean isEmpty(){
        return bst.isEmpty();
    }

    @Override
    public boolean contains(E e){
        return bst.contains(e);
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }
}

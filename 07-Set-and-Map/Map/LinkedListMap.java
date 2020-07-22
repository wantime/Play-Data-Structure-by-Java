public class LinkedListMap<K, V> implements Map<K, V> {

    private LinkedList<K, V> list;

    @Override
    public void add(K key, V value) {
        list.add(key, value);
    }

    @Override
    public V remove(K key) {
        return list.removeElement(key);
    }

    @Override
    public boolean contains(K key) {
        return list.contains(key);
    }

    @Override
    public V get(K key) {
        return list.get(key);
    }

    @Override
    public void set(K key, V newValue) {
        list.set(key, newValue);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}

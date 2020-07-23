import java.util.TreeMap;

public class HashTable<K, V> {

    private TreeMap<K, V>[] hashTable;
    private int M;
    private int size;



    public HashTable(int M){
        this.M = M;
        size = 0;
        hashTable = new TreeMap[M];
        for (int i = 0; i < M; i++)
            hashTable[i] = new TreeMap<>();

    }

    /**
     * 无参数构造函数
     */
    public HashTable(){
        this(97);
    }

    /**
     * 哈希函数，借用了Java的hashcode方法，如有需要，可以重载hashcode方法
     * @param key
     * @return
     */
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    /**
     * 获取哈希表中元素的个数
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 判断元素是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 向哈希表中添加元素
     * @param key   一个hash[key]可能对应多个key，因此是两层TreeMap
     * @param value
     */
    public void add(K key, V value){
        TreeMap<K, V> map = hashTable[hash(key)];

        if ( map.containsKey(key) )
            map.put(key, value);
        else{
            map.put(key, value);
            size ++;
        }
    }

    /**
     * 删去某个元素
     * @param key
     * @return
     */
    public V remove(K key) {

        TreeMap<K, V> map = hashTable[hash(key)];
        V ret = null;

        if (map.containsKey(key)) {
            ret = map.get(key);
            map.remove(key);
            size--;
        }
        return ret;
    }

    /**
     * 修改指定值
     * @param key
     * @param newValue
     */
    public void set(K key, V newValue){
        TreeMap<K, V> map = hashTable[hash(key)];
        if(!map.containsKey(key))
            throw new IllegalArgumentException(key + "doesn't exist!");
        map.put(key, newValue);
    }

    /**
     * 是否包含某个键
     * @param key
     * @return
     */
    public boolean contains(K key){
        return hashTable[hash(key)].containsKey(key);
    }

    /**
     * 查询某个函数
     * @param key
     * @return
     */
    public V get(K key){
        return hashTable[hash(key)].get(key);
    }

}

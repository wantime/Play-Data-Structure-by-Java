import java.util.Queue;

public class UnionFind1 implements UF {

    private int[] id;

    public UnionFind1(int size){

        id = new int[size];
        //  初始化时有size个元素，每个元素是自己是一类
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }


    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int p){
        if(p < 0 && p >= id.length)
            throw new IllegalArgumentException("p is out of bound.");
        return id[p];
    }

    @Override
    public void unionElement(int p, int q) {

        int pID = find(p);
        int qID = find(q);

        if(pID == qID)
            return;

        for (int i = 0; i < id.length; i++) {
            if(id[i] == pID)
                id[i] = qID;
        }
    }
}

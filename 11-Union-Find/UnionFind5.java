public class UnionFind5 implements UF {

    private int[] parent;
    private int[] rank;   //  rank[i]存储以i为根的集合所表示的树的级别

    public UnionFind5(int size){

        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int p){

        if(p < 0 && p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");

        while (p != parent[p]) {
            //  路径压缩，让当前节点指向它父亲节点的父亲节点
            //      1           1
            //     /           / \
            //    2    ->     2   3
            //   /
            //  3
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElement(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot)
            return;

        // 根据两个元素所在树的rank不同判断合并方向
        // 将rank低的集合合并到rank高的集合上
        if(rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if (rank[pRoot] > rank[qRoot])
            parent[qRoot] = pRoot;
        else {
            parent[qRoot] = pRoot;
            rank[pRoot] ++;
        }
    }
}

import sun.font.GlyphLayout;

import java.util.*;
import java.util.function.BinaryOperator;

public class Dijkstra {

    private WeightedGraph G;
    private int s;
    private int[] dis;
    private boolean[] visited;
    private int[] pre;


    private class Node implements Comparable<Node>{

        public int v, dis;

        public Node(int v, int dis){
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node another) {
            return this.dis - another.dis;
        }
    }

    public Dijkstra(WeightedGraph G, int s){

        this.G = G;
        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;
        pre = new int[G.V()];
        Arrays.fill(pre, -1);
        pre[s] = s;

        visited = new boolean[G.V()];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        while (!pq.isEmpty()){

            int cur = pq.remove().v;
            if(visited[cur]) continue;

            visited[cur] = true;
            for (int w: G.adj(cur))
                if(!visited[w]) {
                    if (G.getWeight(cur, w) + dis[cur] < dis[w]) {
                        //  不使用Math.min 处于减少赋值次数的考虑 ?
                        dis[w] = G.getWeight(cur, w) + dis[cur];
                        pq.add(new Node(w, dis[w]));
                        pre[w] = cur;
                    }
                }
        }
    }

    public boolean isConnectedTo(int v){
        G.validateVertex(v);
        return visited[v];
    }

    public int distTo(int v){

        G.validateVertex(v);
        return dis[v];
    }

    public Iterable<Integer> path(int t){

        G.validateVertex(t);
        int cur = t;
        ArrayList<Integer> res = new ArrayList<>();
        while (cur != s){
            res.add(cur);
            cur  = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        WeightedGraph g = new WeightedGraph("g.txt");
        Dijkstra dij = new Dijkstra(g, 0);
        for (int v = 0; v < g.V(); v ++)
            System.out.print(dij.distTo(v) + " ");
        System.out.println();
    }
}

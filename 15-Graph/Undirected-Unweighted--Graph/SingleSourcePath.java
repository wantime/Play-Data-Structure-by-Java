import java.lang.reflect.Array;
import java.util.*;

public class SingleSourcePath {

    //  保证用户无法修改
    private Graph G;
    private int s;

    private boolean[] visited;
    private int[] pre;


    public SingleSourcePath(Graph G, int s){

        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            pre[i] = -1;
        }
        bfs(s);
    }

    private void bfs(int s){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        while (!queue.isEmpty()){
            int v = queue.remove();

            for (int w: G.adj(v)) {
                if(!visited[w]){
                    pre[w] = v;
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

    private Iterable<Integer> path(int t){

        G.validateVertex(t);
        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnectedTo(t)) return res;

        int cur = t;
        while (cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {

        Graph g = new Graph("g.txt");
        SingleSourcePath path = new SingleSourcePath(g, 0);
        System.out.println( "0 -> 4 : " + path.path(4) );

    }
}

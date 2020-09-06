import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Unweighted Shortest Path
 */
public class USPath {

    private Graph G;
    private int s;
    private int t;

    private boolean[] visited;
    private int[] pre;
    private int[] dis;

    public USPath(Graph G, int s, int t){

        this.G = G;
        this.s = s;
        this.t = t;

        visited = new boolean[G.V()];
        pre = new int[G.V()];
        dis = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            dis[i] = -1;
        }

        bfs(s);
    }
    private void bfs(int s){

        visited[s] = true;
        pre[s] = s;
        dis[s] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()){

            int v = queue.remove();

            for (int w: G.adj(v)){
                if(!visited[w]){

                    queue.add(w);

                    pre[w] = v;
                    dis[w] = dis[v] + 1;
                    visited[w] = true;

                    if(w == t)
                        return;
                }
            }

        }
    }
    public boolean isConnected(){
        return visited[t];
    }

    public Iterable<Integer> path(){

        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnected()) return res;

        int cur = t;
        while (cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }
}

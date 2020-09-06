import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Unweighted Single Source Path
 */
public class USSSPath {
    //  保证用户无法修改
    private Graph G;
    private int s;

    private boolean[] visited;
    private int[] pre;
    private int[] distance;

    public USSSPath(Graph G, int s){

        this.G = G;
        this.s = s;

        visited = new boolean[G.V()];
        pre = new int[G.V()];
        distance = new int[G.V()];

        for (int i = 0; i < G.V(); i++) {
            pre[i] = -1;
        }
        bfs(s);

        for (int i = 0; i < G.V(); i++)
            System.out.print(distance[i] + "\t");
        System.out.println();
    }

    private void bfs(int s){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        distance[s] = 0;
        while (!queue.isEmpty()){
            int v = queue.remove();

            for (int w: G.adj(v)) {
                if(!visited[w]){
                    queue.add(w);
                    pre[w] = v;
                    visited[w] = true;
                    distance[w] = distance[v] + 1;
                }
            }
        }
    }

    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

    public int dis(int t){
        G.validateVertex(t);
        return distance[t];
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
        USSSPath usssPath = new USSSPath(g, 0);
        System.out.println("0 -> 6 : " + usssPath.path(6));
        System.out.println(usssPath.dis(2));
    }
}

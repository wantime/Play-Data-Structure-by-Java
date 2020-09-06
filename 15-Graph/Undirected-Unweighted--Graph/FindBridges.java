
import java.util.ArrayList;

public class FindBridges {

    private Graph G;
    private boolean[] visited;

    private int ord[];
    private int low[];
    private int cnt;

    private ArrayList<Edge> res;

    public FindBridges(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        res = new ArrayList<>();

        ord = new int[G.V()];
        low = new int[G.V()];
        cnt = 0;

        for (int v = 0; v < G.V(); v ++){
            if(!visited[v])
                dfs(v, v);
        }
    }

    private void dfs(int v, int parent){

        visited[v] = true;
        ord[v] = cnt++;
        low[v] = ord[v];
        for (int w : G.adj(v))
            if(!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if(low[w] > ord[v]){
                    //  v-w是桥
                    res.add(new Edge(v, w));
                }
            }
            else if(w != parent)
                low[v] = Math.min(low[v], low[w]);
    }

    public ArrayList<Edge> result(){
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        FindBridges fb = new FindBridges(g);
        System.out.println(fb.result());
    }
}

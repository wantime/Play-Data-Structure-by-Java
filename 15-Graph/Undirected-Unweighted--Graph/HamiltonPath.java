import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class HamiltonPath {

    //  保证用户无法修改
    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private int end;

    public HamiltonPath(Graph G, int s){

        if(s < 0 || s > G.V())
            throw new IllegalArgumentException("s is illegal !");
        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        end = -1;
        dfs(s, s, G.V());
    }
    private boolean dfs(int v, int parent, int left){

        visited[v] = true;
        pre[v] = parent;
        left --;
        if(left == 0){
            end = v;
            return true;
        }

        for(int w: G.adj(v)) {
            if (!visited[w])
                if (dfs(w, v, left))
                    return true;
        }
        visited[v] = false;
        return false;
    }

    public ArrayList<Integer> result(){

        ArrayList<Integer> res = new ArrayList<>();
        if(end == -1)
            return res;
        int cur = end;
        while (res.size() < G.V()){
            res.add(cur);
            cur = pre[cur];
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {

        Graph g = new Graph("g.txt");
        HamiltonPath h = new HamiltonPath(g, 2);
        System.out.println(h.result());
    }
}

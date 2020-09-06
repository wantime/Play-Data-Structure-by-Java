import java.lang.reflect.Array;
import java.util.*;

public class BipartitionDetection {

    //  保证用户无法修改
    private Graph G;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetection(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            colors[i] = -1;
        }

        for (int v = 0; v < G.V(); v++) {
            if(!visited[v])
                if(!bfs(v)) {
                    isBipartite = false;
                    break;
                }
        }
    }
    private boolean bfs(int s){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        colors[s] = 0;

        while (!queue.isEmpty()){
            int v = queue.remove();

            for (int w: G.adj(v)){
                if(!visited[w]){
                    queue.add(w);
                    visited[w] = true;

                    colors[w] = 1 - colors[v];
                }
                else if(colors[w] == colors[v])
                    return false;
            }
        }
        return true;
    }

    public boolean isBipartite(){
        return isBipartite;
    }

    public Iterable<Integer>[] group(){
        if(!isBipartite)
            throw new IllegalArgumentException("is not Bipartite!");
        ArrayList<Integer>[] group = new ArrayList[2];
        for (int i = 0; i < group.length; i++) {
            group[i] = new ArrayList<>();
        }
        for (int v = 0; v < G.V(); v++) {
            group[colors[v]].add(v);
        }
        return group;
    }



    public static void main(String[] args) {


    }
    public class Node{
        public int val;
        public List<Node> children;

    }

}

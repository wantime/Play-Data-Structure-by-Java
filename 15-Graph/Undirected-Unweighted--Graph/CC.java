import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CC {

    //  保证用户无法修改
    private Graph G;
    private int ccount = 0;
    private int[] visited;


    public CC(Graph G){

        this.G = G;
        visited = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            visited[i] = -1;
        }

        for (int v = 0; v < G.V(); v++) {
            if(visited[v] == -1) {
                bfs(v, ccount);
                ccount ++;
            }
        }
    }

    private void bfs(int s, int ccid){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = ccid;

        while (!queue.isEmpty()){
            int v = queue.remove();

            for (int w: G.adj(v)) {
                if(visited[w] == -1){
                    queue.add(w);
                    visited[w] = ccid;
                }
            }
        }
    }

    public int count(){
        return ccount;
    }
    public boolean isConnected(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }
    public Iterable<Integer>[] components(){

        ArrayList<Integer>[] res = new ArrayList[ccount];
        for (int i = 0; i < ccount; i++)
            res[i] = new ArrayList<>();

        for (int v = 0; v < G.V(); v++)
            res[visited[v]].add(v);

        return res;
    }


    public static void main(String[] args) {

        Graph g = new Graph("g.txt");
        CC cc = new CC(g);

        Iterable<Integer>[] comp = cc.components();
        for (int i = 0; i < comp.length; i++) {
            System.out.print(i + " :\t");
            for (int w: comp[i])
                System.out.print(w + "\t");
            System.out.println();
        }
    }
}

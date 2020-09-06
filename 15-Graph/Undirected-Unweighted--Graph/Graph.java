
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class Graph implements Cloneable{

    private int V;  //  顶点个数
    private int E;  //  边的数量
    private TreeSet<Integer>[] adj;    //  邻接表

    public Graph(String filename){

        File file = new File(filename);
        try ( Scanner scanner = new Scanner(file) ){

            V = scanner.nextInt();
            if(V < 0)
                throw new IllegalArgumentException("V must be non-negative");
            adj = new TreeSet [V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<>();
            }

            E = scanner.nextInt();
            if(E < 0)
                throw new IllegalArgumentException("E must be non-negative");

            for (int i = 0; i < E; i++) {

                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);

                if(a == b) throw new IllegalArgumentException("Self Loop is Detected!");
                if(adj[a].contains(b)) throw new IllegalArgumentException("Parallel Edges are Detected!");

                adj[a].add(b);
                adj[b].add(a);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    /**
     * 判断某条边是否存在
     * @param v
     * @param w
     * @return
     */
    public boolean hasEdge(int v, int w){

        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    /**
     * 获得顶点V的邻点
     * @param v 指定顶点
     * @return
     */
    public Iterable<Integer> adj(int v){

        validateVertex(v);
        return adj[v];
    }

    /**
     * 获得顶点V的度
     * @param v
     * @return
     */
    public int degree(int v){
        validateVertex(v);
        return adj[v].size();
    }

    public void removeEdge(int v, int w){

        validateVertex(v);
        validateVertex(w);

        adj[v].remove(w);
        adj[w].remove(v);
    }

    @Override
    public Object clone(){

        try {
            Graph cloned = (Graph) super.clone();
            cloned.adj = new TreeSet[V];
            for (int v = 0; v < V; v ++){
                cloned.adj[v] = new TreeSet<>();
                for (int w : adj[v])
                    cloned.adj[v].add(w);
            }
            return cloned;
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断传入的顶点是否有效
     * @param v
     */
    public void validateVertex(int v){
        if(v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + "is invalid");
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d : ", v));
            for (int w: adj[v]) {
                sb.append(String.format("%d\t", w));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        Graph adj = new Graph("g.txt");
        System.out.println(adj);
    }
}

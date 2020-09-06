import java.lang.reflect.Array;
import java.util.ArrayList;

public class GraphDFS {

    //  保证用户无法修改
    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();
    private Graph G;
    private int ccount = 0;
    private boolean[] visited;


    public GraphDFS(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if(!visited[v]) {
                dfs(v);
                ccount ++;
            }
        }

    }
    private void dfs(int v){

        visited[v] = true;
        pre.add(v);
        for(int w: G.adj(v))
            if(!visited[w])
                dfs(w);
        post.add(v);
    }

    /**
     * 前序遍历结果
     * @return 返回可遍历对象
     */
    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public int getCcount(){
        return ccount+1;
    }

    public static void main(String[] args) {

        Graph g = new Graph("g.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println(graphDFS.pre);
        System.out.println(graphDFS.post);
    }
}

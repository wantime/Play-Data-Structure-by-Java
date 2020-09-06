import java.util.*;


public class Path {

    //  保证用户无法修改
    private Graph G;
    private int s;
    private int t;

    private boolean[] visited;
    private int[] pre;


    public Path(Graph G, int s, int t){

        G.validateVertex(s);
        G.validateVertex(t);
        this.G = G;
        this.s = s;
        this.t = t;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        Arrays.fill(pre, -1);
        bfs();

    }
    private void bfs(){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        if(s == t) return;

        while (!queue.isEmpty()){

            int v = queue.remove();
            for (int w: G.adj(v)){
                if(!visited[w]){

                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;

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

    public static void main(String[] args) {

        Graph g = new Graph("g.txt");
        Path path = new Path(g, 0,6);
        System.out.println( "0 -> 6 : " + path.path() );
    }
}

import java.util.LinkedList;
import java.util.Queue;

public class CycleDetection {

    //  保证用户无法修改
    private Graph G;
    private boolean[] visited;
    private boolean hasCycle = false;
    private int[] pre;

    public CycleDetection(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            pre[i] = -1;
        }

        for (int v = 0; v < G.V(); v++)
            if(!visited[v])
                if( bfs(v) ) {
                    hasCycle = true;
                    break;
                }
    }
    /**
     * 从顶点s出发，判断图中是否有环
     * @param s
     * @return
     */
    private boolean bfs(int s){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;

        while (!queue.isEmpty()){
            int v = queue.remove();
            for (int w: G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                } else if (w != pre[v])
                    return true;
            }
        }
        return false;
    }

    public boolean HasCycle(){
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle);
    }
}

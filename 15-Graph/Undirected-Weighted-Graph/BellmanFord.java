import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BellmanFord {

    private WeightedGraph G;
    private int s;
    private int[] dis;
    private int[] pre;
    private boolean hasNegativeCycle;

    public BellmanFord(WeightedGraph G, int s){

        this.G = G;
        G.validateVertex(s);
        this.s = s;

        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[s] = 0;

        pre = new int[G.V()];
        Arrays.fill(pre, -1);
        pre[s] = s;

        for (int pass = 1; pass < G.V(); pass ++){

            for (int v = 0; v < G.V(); v ++)
                for (int w: G.adj(v))
                    if(dis[v] != Integer.MAX_VALUE && dis[v] + G.getWeight(v, w) < dis[w]) {
                        dis[w] = dis[v] + G.getWeight(v, w);
                        pre[w] = v;
                    }
        }
        for (int v = 0; v < G.V(); v ++)
            for (int w: G.adj(v))
                if(dis[v] + G.getWeight(v, w) < dis[w])
                    hasNegativeCycle = true;
    }

    public boolean HasNegCycle(){
        return hasNegativeCycle;
    }

    public boolean isConnectedTo(int v){
        G.validateVertex(v);
        return dis[v] != Integer.MAX_VALUE;
    }

    public int disTo(int v){
        G.validateVertex(v);
        if(hasNegativeCycle)
            throw new RuntimeException("exist negative cycle!");
        return dis[v];
    }

    public Iterable<Integer> path(int t){

        ArrayList<Integer> res= new ArrayList<>();
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

        WeightedGraph g = new WeightedGraph("g.txt");
        BellmanFord bf = new BellmanFord(g, 0);

        if(!bf.hasNegativeCycle){
            for (int v = 0; v < g.V(); v ++)
                System.out.print(bf.disTo(v) + " ");
            System.out.println(bf.path(3));
        }
        else
            System.out.println("exist negative cycle.");
    }
}

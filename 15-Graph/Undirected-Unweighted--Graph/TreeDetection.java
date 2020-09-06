public class TreeDetection {

    private CC cc;
    private CycleDetection cycleDetection;
    private boolean isTree = false;

    public TreeDetection(Graph G){
        cc = new CC(G);
        cycleDetection = new CycleDetection(G);

        if(cc.count() == 1 && !cycleDetection.HasCycle())
            isTree = true;
    }

    public boolean isTree(){
        return isTree;
    }

}

import java.util.ArrayList;

public class Sorter {

    protected ArrayList<Integer> topSort() {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            used.add(0);
        }
        for (int i = 0; i < graph.size(); i++) {
            if (used.get(i).equals(2)) {
                continue;
            }
            dfs(ans, i);
            if (err) {
                break;
            }
        }
        if (err) {
            return cycle;
        }
        return ans;
    }

    private void dfs(ArrayList<Integer> ans, Integer v) {
        used.set(v, 1);
        for (Integer child : graph.get(v)) {
            if (used.get(child).equals(0)) {
                dfs(ans, child);
            }
            if (used.get(child).equals(1)) {
                err = true;
                cycle.add(v);
                return;
            }
        }
        ans.add(v);
        used.set(v, 2);
    }

    protected void setGraph(ArrayList<ArrayList<Integer>> orig) {
        graph = orig;
    }

    private ArrayList<ArrayList<Integer>> graph;

    private final ArrayList<Integer> cycle = new ArrayList<>();
    private final ArrayList<Integer> used = new ArrayList<>();
    protected boolean err = false;
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FullProcess {
    protected void work() {
        getRoot();
        readFiles();
        ArrayList<Integer> ans = sortFiles();
        if (err) {
            System.out.println("ERROR: cycle");
        }
        printFiles(ans);
    }

    private ArrayList<Integer> sortFiles() {
        Sorter srt = new Sorter();
        srt.setGraph(graph);
        ArrayList<Integer> ans = srt.topSort();
        err = srt.err;
        Collections.reverse(ans);
        return ans;
    }

    private void readFiles() {
        Reader myReader = new Reader();
        myReader.setBoss(this);
        File root = new File(rootPath);
        myReader.numerate(root);
        graph = new ArrayList<>(allFiles.size());
        for (int i = 0; i < allFiles.size(); i++) {
            graph.add(new ArrayList<>());
        }
        myReader.collectInformation(root);
    }

    private void printFiles(ArrayList<Integer> ans) {
        File[] inversion = new File[graph.size()];
        for (File myFl : allFiles.keySet()) {
            inversion[allFiles.get(myFl)] = myFl;
        }
        for (Integer i : ans) {
            File now = inversion[i];
            if (err) {
                System.out.println(now.toString());
                continue;
            }
            try {
                Scanner myReader = new Scanner(now);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    private void getRoot() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a root folder path: ");
        rootPath = sc.nextLine();
    }

    protected String rootPath;
    protected Map<File, Integer> allFiles = new HashMap<>();

    protected ArrayList<ArrayList<Integer>> graph;
    private boolean err;
}

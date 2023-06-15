import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Reader {
    void numerate(File current) {
        if (current.isDirectory()) {
            for (File subCat : Objects.requireNonNull(current.listFiles())) {
                numerate(subCat);
            }
        } else {
            boss.allFiles.put(current, cnt);
            cnt++;
        }
    }

    void collectInformation(File current) {
        if (current.isDirectory()) {
            for (File subCat : Objects.requireNonNull(current.listFiles())) {
                collectInformation(subCat);
            }
        } else {
            parseFile(current);
        }
    }

    void parseFile(File current) {
        try {
            Scanner myReader = new Scanner(current);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(" ", 2);
                if (parts.length != 2 || !parts[0].equals("require")) {
                    continue;
                }
                String fullPath = boss.rootPath + "\\" + parts[1].substring(1, parts[1].length() - 1);
                File fl = new File(fullPath);
                try {
                    Integer parent = boss.allFiles.get(fl);
                    Integer child = boss.allFiles.get(current);
                    boss.graph.get(parent).add(child);
                } catch (NullPointerException e) {
                    String bad = current.toString();
                    System.out.println("Require Error in " + bad);
                    System.exit(0);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    protected void setBoss(FullProcess proc) {
        boss = proc;
    }
    private Integer cnt = 0;
    private FullProcess boss;

}

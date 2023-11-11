import models.BTree;
import utils.BTreeMethodsMetrics;
import utils.RandomNumbersGenerator;
import java.io.RandomAccessFile;

public class Index {
    private static final String filename = "b-tree.txt";
    private static final int keysArrayLength = 10000;
    private static final int[] keys = RandomNumbersGenerator.generateRandomArray(keysArrayLength);
    public static BTree btree;

    public static void main(String[] args) {
        try {
            RandomAccessFile file = new RandomAccessFile(filename, "rw");
            file.setLength(0);
            btree = new BTree(file);
            BTreeMethodsMetrics.insertKeysIntoBtree(keys, btree);
            BTreeMethodsMetrics.searchKeysFromBTree(keys, keysArrayLength, btree);
            BTreeMethodsMetrics.deleteKeysFromBTree(keys, keysArrayLength - 1, btree);
            file.close();
        } catch (Exception err) {
            System.out.println("An error has occurred: " + err.getMessage());
        }
    }
}

package utils;

import models.BTree;
import java.io.IOException;

public class BTreeMethodsMetrics {
    private static final int defaultRandomKeysArrayLength = 5000;

    public static void insertKeysIntoBtree(int[] keysToInsert, BTree btree) throws IOException {
        

        TimeMeter.init();

        for (int key : keysToInsert) {
            btree.insertNode(key);
        }
        TimeMeter.end("insertArrayKeysInBtree");

        // btree.printBTree();

    }

    public static void searchKeysFromBTree(int[] insertedKeys, int quantityKeysToSearch, BTree btree) throws IOException {


        final int[] keysToSearch = RandomNumbersGenerator.pickRandomValuesFromExistentArray(insertedKeys, quantityKeysToSearch);

        TimeMeter.init();

        for (int key : keysToSearch) {
            btree.searchNode(null, key);
        }

        TimeMeter.end("searchKeysFromBTree");
    }

    public static void deleteKeysFromBTree(int[] insertedKeys, int quantityKeysToDelete, BTree btree) throws IOException {

        final int[] keysToDelete = RandomNumbersGenerator.pickRandomValuesFromExistentArray(insertedKeys, quantityKeysToDelete);
        
        TimeMeter.init();
        for (int key : keysToDelete) {
            btree.RemoveNode(key);
        }
        TimeMeter.end("deleteKeysFromBTree");
        // btree.printBTree();

    }
}

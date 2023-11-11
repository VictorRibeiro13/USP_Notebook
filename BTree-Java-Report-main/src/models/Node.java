package models;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class Node extends BaseEntityModel {
    public int leaf, keysNum, address;
    public int[] keys, children;

    public Node(boolean isLeaf, RandomAccessFile file) throws IOException {
        this.leaf = isLeaf ? TRUE : FALSE;

        if (!isLeaf()) {
            this.children = new int[MAX_CHILDREN_SIZE];
            Arrays.fill(this.children, NULL);
        }

        this.keys =  new int[MAX_NODE_SIZE];
        this.keysNum = 0;
        this.address = (int) file.length();
    }

    public boolean isLeaf() {
        return this.leaf == TRUE;
    }

    public boolean isFull() {
        return this.keysNum == MAX_NODE_SIZE;
    }

    public void printNode() {
        System.out.println("Address: " + this.address);
        System.out.println("Leaf: " + (this.leaf == TRUE));
        System.out.println("Keys Num: " + this.keysNum);
        System.out.print("Keys: [");

        for (int i = 0; i < this.keysNum; i++) {
            System.out.print(" " + this.keys[i]);
        }

        System.out.print(" ]");

        System.out.println();

        if (this.leaf == FALSE) {
            System.out.print("Children:");

            for (int i = 0; i < this.keysNum + 1; i++) {
                System.out.print(" " + this.children[i]);
            }
        }

    }

    @Override
    public String toString() {
        return "Node{" +
                "leaf=" + this.leaf +
                ", keysNum=" + this.keysNum +
                ", keys=" + Arrays.toString(this.keys) +
                ", children=" + Arrays.toString(this.children) +
                '}';
    }
}

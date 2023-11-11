package models;

import java.io.*;
import java.nio.ByteBuffer;

public class BTree extends BaseEntityModel {
    private final RandomAccessFile file;
    private Node root;

    public BTree(RandomAccessFile file) throws IOException {
        this.file = file;

        if (file.length() > 0) {
            this.root = readNode(0);
            return;
        }

        Node node = new Node(true, file);
        node.address = 0;
        this.root = node;
        writeNode(node);

    }

    public void insertNode(int key) throws IOException {
        Node aux = root;
        if (root.isFull()) {
            Node newNode = new Node(false, file);
            newNode.keysNum = 0;
            newNode.children[0] = aux.address;
            writeNode(newNode);
            splitNode(newNode, 0);
            insertNonFull(newNode, key);
            this.root = newNode;
            return;
        }

        insertNonFull(aux, key);
    }

    public Node searchNode(Node rootTree, int key) throws IOException {
        if (rootTree == null)
            rootTree = root;

        int i = 0;
        while (i < rootTree.keysNum && key > rootTree.keys[i])
            i++;

        if (i < rootTree.keysNum && key == rootTree.keys[i])
            return rootTree;

        if (rootTree.isLeaf())
            return null;

        return searchNode(readNode(rootTree.children[i]), key);
    }

    public void splitNode(Node nodeFather, int nth) throws IOException {
        Node nodeToBeSplit = readNode(nodeFather.children[nth]);
        Node node = new Node(nodeToBeSplit.isLeaf(), file);
        node.keysNum = HALF_NODE_SIZE;

        for (int j = 0; j < HALF_NODE_SIZE; j++) {
            node.keys[j] = nodeToBeSplit.keys[j + t];
        }

        if (!nodeToBeSplit.isLeaf()) {
            for (int j = 0; j < t; j++) {
                node.children[j] = nodeToBeSplit.children[j + t];
            }
        }

        nodeToBeSplit.keysNum = HALF_NODE_SIZE;
        for (int j = nodeFather.keysNum; j >= nth + 1; j--) {
            nodeFather.children[j + 1] = nodeFather.children[j];
        }

        nodeFather.children[nth + 1] = node.address;

        for (int j = nodeFather.keysNum - 1; j >= nth; j--) {
            nodeFather.keys[j + 1] = nodeFather.keys[j];
        }

        nodeFather.keys[nth] = nodeToBeSplit.keys[HALF_NODE_SIZE];
        nodeFather.keysNum++;

        writeNode(nodeToBeSplit);
        writeNode(node);
        writeNode(nodeFather);
    }

    public void RemoveNode(int key) throws IOException {
        Remove(this.root, key);
    }

    public Node readNode(int end) throws IOException {
        if (file.length() == 0 || end == NULL)
            return null;

        file.seek(end);
        file.readInt();
        boolean isLeaf = file.readInt() == TRUE;
        byte[] bytes = isLeaf ? new byte[LEAF_NODE_SIZE - INT_BYTES] : new byte[INTERN_NODE_SIZE - INT_BYTES];
        file.read(bytes);
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        Node node = new Node(isLeaf, file);
        node.keysNum = readInt(in);
        node.address = end;

        for (int i = 0; i < MAX_NODE_SIZE; i++) {
            node.keys[i] = readInt(in);
        }

        if (!isLeaf) {
            for (int i = 0; i < MAX_NODE_SIZE + 1; i++) {
                node.children[i] = readInt(in);
            }
        }

        return node;
    }

    public void writeNode(Node node) throws IOException {

        int nBytes = node.isLeaf() ? LEAF_NODE_SIZE : INTERN_NODE_SIZE;
        ByteArrayOutputStream out = new ByteArrayOutputStream(nBytes);
        writeInt(out, node.address);
        writeInt(out, node.leaf);
        writeInt(out, node.keysNum);

        for (int i = 0; i < node.keys.length; i++) {
            writeInt(out, node.keys[i]);
        }

        if (!node.isLeaf()) {
            for (int i = 0; i < node.children.length; i++) {
                writeInt(out, node.children[i]);
            }
        }

        file.seek(node.address);
        file.write(out.toByteArray());
    }

    public void printBTree() throws IOException {
        int actualAddress = 0;

        while (actualAddress < file.length()) {
            Node node = readNode(actualAddress);
            System.out.println("\n ====== Node ====== ");
            node.printNode();
            actualAddress += node.isLeaf() ? LEAF_NODE_SIZE : INTERN_NODE_SIZE;
        }
    }

    private void insertNonFull(Node node, int key) throws IOException {
        int i = node.keysNum - 1;

        if (node.isLeaf()) {
            while (i >= 0 && key < node.keys[i]) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }
            node.keys[i + 1] = key;
            node.keysNum++;
            writeNode(node);
            return;
        }

        while (i >= 0 && key < node.keys[i])
            i--;
        i++;

        Node tmp = readNode(node.children[i]);

        if (tmp.isFull()) {
            splitNode(node, i);
            if (key > node.keys[i]) {
                i++;
            }
        }
        insertNonFull(readNode(node.children[i]), key);
    }

    private void Remove(Node x, int k) throws IOException {
        int i = findKey(x, k);
        if (x.isLeaf()) {
            if (i > x.keysNum - 1 || x.keys[i] != k) {
                return;
            }
            x.keys[i] = NULL;
            for (int j = i + 1; j < x.keys.length; j++) {
                x.keys[j - 1] = x.keys[j];
            }
            x.keysNum--;
            writeNode(x);
            return;
        }
        if (i < x.keysNum && x.keys[i] == k) {
            Node predChild = readNode(x.children[i]);
            Node succChild = readNode(x.children[i + 1]);
            if (predChild.keysNum >= t) {
                int pred = getPred(predChild);
                Remove(predChild, pred);
                x.keys[i] = pred;
                writeNode(x);
                return;
            }
            if (succChild.keysNum >= t) {
                int sucessor = getSucc(succChild);
                Remove(succChild, sucessor);
                x.keys[i] = sucessor;
                writeNode(x);
                return;
            }
            predChild = merge(x, i);
            Remove(predChild, k);
            return;
        }
        Node subTree = readNode(x.children[i]);
        if (subTree.keysNum == t - 1) {
            Node lt = null;
            Node rt = null;
            if (i != 0) {
                lt = readNode(x.children[i - 1]);
            }
            if (i != x.keysNum) {
                rt = readNode(x.children[i + 1]);
            }

            if (lt != null && lt.keysNum >= t) {
                for (int j = subTree.keysNum - 1; j >= 0; --j) {
                    subTree.keys[j + 1] = subTree.keys[j];
                }
                if (!subTree.isLeaf()) {
                    for (int j = subTree.keysNum; j >= 0; --j) {
                        subTree.children[j + 1] = subTree.children[j];
                    }
                }
                subTree.keys[0] = x.keys[i - 1];
                if (!subTree.isLeaf()) {
                    subTree.children[0] = lt.children[lt.keysNum];
                }
                x.keys[i - 1] = lt.keys[lt.keysNum - 1];
                subTree.keysNum++;
                lt.keysNum--;
                writeNode(x);
                writeNode(subTree);
                writeNode(lt);
                return;
            }
            if (rt != null && rt.keysNum >= t) {
                subTree.keys[subTree.keysNum] = x.keys[i];
                if (!subTree.isLeaf()) {
                    subTree.children[subTree.keysNum + 1] = rt.children[0];
                }
                x.keys[i] = rt.keys[0];

                for (int j = 1; j < rt.keysNum; ++j) {
                    rt.keys[j - 1] = rt.keys[j];
                }

                if (!rt.isLeaf()) {
                    for (int j = 1; j <= rt.keysNum; ++j) {
                        rt.children[j - 1] = rt.children[j];
                    }
                }

                subTree.keysNum++;
                rt.keysNum--;
                writeNode(x);
                writeNode(subTree);
                writeNode(rt);
                return;
            }
            if (i != x.keysNum) {
                subTree = merge(x, i);
            } else if (x.keysNum != 0) {
                subTree = merge(x, i - 1);
            }
        }
        Remove(subTree, k);
    }

    private Node merge(Node node, int i) throws IOException {
        Node child = readNode(node.children[i]);
        Node sibling = readNode(node.children[i + 1]);

        child.keys[t - 1] = node.keys[i];

        for (int j = 0; j < child.keysNum; ++j) {
            child.keys[t + j] = sibling.keys[j];
        }

        if (!child.isLeaf()) {
            for (int j = 0; j <= sibling.keysNum; ++j) {
                child.children[j + t] = sibling.children[j];
            }
        }

        for (int j = i + 1; j < node.keysNum; ++j) {
            node.keys[j - 1] = node.keys[j];
        }

        for (int j = i + 2; j <= node.keysNum; ++j) {
            node.children[j - 1] = node.children[j];
        }

        node.keysNum--;
        child.keysNum += sibling.keysNum++;
        writeNode(child);
        writeNode(node);
        return child;
    }

    private int findKey(Node x, int k) {
        int i = 0;
        while (i < x.keysNum && k > x.keys[i]) {
            i++;
        }
        return i;
    }

    private int getSucc(Node x) throws IOException {
        if (x.isLeaf()) {
            return x.keys[0];
        }
        return getSucc(readNode(x.children[0]));
    }

    private int getPred(Node node) throws IOException {
        if (node.isLeaf()) {
            return node.keys[node.keysNum - 1];
        }
        return getPred(readNode(node.children[node.keysNum]));
    }

    private int readInt(ByteArrayInputStream in) {
        byte[] bInt = new byte[INT_BYTES];
        in.read(bInt, 0, INT_BYTES);
        return ByteBuffer.wrap(bInt).asIntBuffer().get();
    }

    private void writeInt(ByteArrayOutputStream out, int i) {
        byte[] num = ByteBuffer.allocate(INT_BYTES).putInt(i).array();
        out.write(num, 0, INT_BYTES);
    }

    private void changeRoot(Node newRoot) throws IOException {
        this.root = newRoot;
        file.writeInt(this.root.address);
    }
}

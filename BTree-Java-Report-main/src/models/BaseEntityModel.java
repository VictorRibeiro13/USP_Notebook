package models;

public abstract class BaseEntityModel {
    protected static int t = 300;
    protected static final int NULL = -1;
    protected static final int INT_BYTES = 4;
    protected static final int MAX_NODE_SIZE = 2 * t - 1;
    protected static final int HALF_NODE_SIZE = t - 1;
    protected static final int MAX_CHILDREN_SIZE = 2 * t;
    protected static final int INTERN_NODE_SIZE = 3 * 4 + 4 * MAX_NODE_SIZE + 4 * (2 * t);
    protected static final int LEAF_NODE_SIZE = 3 * 4 + 4 * MAX_NODE_SIZE;
    protected static final int TRUE = 1;
    protected static final int FALSE = 0;

    public BaseEntityModel() {}

    public BaseEntityModel(int t) {
        BaseEntityModel.t = t;
    }
}

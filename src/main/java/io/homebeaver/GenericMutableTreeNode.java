package io.homebeaver;

import javax.swing.tree.TreeNode;

public interface GenericMutableTreeNode<TN> extends TreeNode {

    void insert(GenericMutableTreeNode<?> child, int index);
    void remove(int index);
    void remove(GenericMutableTreeNode<?> node);
    void setUserObject(TN object);
    void removeFromParent();
    void setParent(GenericMutableTreeNode<?> newParent);

}

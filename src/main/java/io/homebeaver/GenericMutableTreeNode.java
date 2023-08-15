package io.homebeaver;

import javax.swing.tree.TreeNode;

/*

eine Alternative zu interface javax.swing.tree.MutableTreeNode, die generics nutzt
siehe https://en.wikipedia.org/wiki/Generics_in_Java

Die interface Methoden sind gleich zu MutableTreeNode.
Mit TN == Object kann man MutableTreeNode ersetzen, dann entspricht
MutableTreeNode.setUserObject(Object object)
GenericMutableTreeNode.setUserObject(Object object)

 */
/**
 * 
 * A generic altenative to interface javax.swing.tree.MutableTreeNode
 *
 * @param <TN> the type of tree nodes in the tree
 */
public interface GenericMutableTreeNode<TN> extends TreeNode {

    void insert(GenericMutableTreeNode<?> child, int index);
    void remove(int index);
    void remove(GenericMutableTreeNode<?> node);
    /**
     * Resets the user object of the receiver to <code>object</code>.
     * @param object generic object of Type TN 
     */
    void setUserObject(TN object);
    void removeFromParent();
    void setParent(GenericMutableTreeNode<?> newParent);

}

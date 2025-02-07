package io.homebeaver.uom;

import java.awt.Component;

public interface UoMTreeNodeContainer {

// see public Component Container.add(Component comp)
//     public Component Container.add(String name, Component comp) 
//     public Component Container.add(Component comp, int index)
	/**
	 * a macro like container method to add a UoMTreeNode to a component
	 * @param uomNode UoMTreeNode
	 */
	public Component add(UoMTreeNode uomNode);
	public void setEnabled(boolean enabled);
}

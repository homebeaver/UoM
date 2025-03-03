/*
https://bugs.openjdk.org/browse/JDK-4238829

When a JLabel is used as a ListCellRenderer for JComboBox, 
at the creation time (the first display of the of the JComboBox on the application Frame) 
the editBox is filled with the first item in the list.

BUT when a JPANEL is used as a ListCellRenderer for JComboBox, 
the editBox is not filled at startup time even when we use the instruction setSelectedIndex.
At the first use of the JComboBox (when you deploy the popup list) all the things turns well ...
How can I init my comboBox in the right way ?

The following code testCombo reproduce the related problem :
this code is derived from one used to fix the bug 4098249
I have used swing 1.1.1 beta 1 (the problem occurs also with swing 1.1.0)

-------------
BT2:EVALUATION

Creating a ListCellRenderer with a JPanel doen't seem to display the first selected item 
as the user suggests. However, a JLabel item seems to work fine. 
I created a simpler example RenderTest which displays this bug. 
I'm still working on this.
mark.davidson@Eng 2000-01-18

-----------------------------------------------

The problem is that the BasicComboBoxUI and MetalComboBoxButton is not checking to see 
if the ListCellRenderer is a container. 
If it is then it should indicate to the CellRendererPane that is should call validate 
on the renderer in the paintCurrentValue() method.

The solution is to check to see if the renderer is a panel and set the validate flag if true.

The work around that I documented will work for 1.2.x and 1.3.

mark.davidson@Eng 2000-03-03 

--------------------

BT2:WORK AROUND

The Panel based renderer should override setBounds() and call validate.

For example, adding the following block of code to the container (JPanel) renderer should fix the problem:

public void setBounds(int x, int y, int w, int h) {
super.setBounds(x, y, w, h);
validate();
}

mark.davidson@Eng 2000-03-03 
 */
package samples.krT82822;
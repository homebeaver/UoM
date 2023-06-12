/*

Es geht um Einheiten. Für die physikalischen Grössen gibt es das Internationale Einheitensystem, 
auch SI genannt (Système international d’unités). Das SI beruht auf sieben Basisgrößen
- Länge, Masse, Zeit, ... (siehe https://de.wikipedia.org/wiki/Basisgr%C3%B6%C3%9Fe)

Es gibt auch andere Einheiten. Physikalische, nicht metrische, dir man in die SI Basisgrößen umrechnen kann,
z.B. Seemeile, usw. Und nicht physikalische, wie Währungen mit den Einheiten Euro oder Dollar. 
Im Handel sind die Eiheiten Stück, 6Pack gebräuchlich.

Mit Objekten der Klasse UoM (Units of Measure) kann man Einheiten beschreiben,
oder auch Einheiten zusammenfassen:
- isQuantity() == true , dann handelt es sich um eine Einheit und es gibt ein Symbol, z.B. "m" für Meter
- isQuantity() == false , dann handelt es sich um eine hierarchische Zusammenfassung für Einheiten,
die sieben Basisgrößen Länge, Masse, Zeit, Stromstärke, Temperatur und Lichtstärke sind solche Zusammenfassungen.

Mit UoMTreeNode kann man Bäume, also hierarchische Strukturen von UoM-Einheiten beschreiben
und sie grafisch darstellen. UoMTreeNode kapselt eine UoM Objektinstanz, 
auf die man mit getUoM() zugreiffen kann.

Ableitungen:

class UoMTreeNode extends GenericTreeNode<UoM>
class DirectoryTreeNode extends UoMTreeNode // Instanzen können Baumblätter sein
class QuantityTreeNode extends UoMTreeNode // isQuantity() ist immer true, Instanzen sind immer Baumblätter


 */
package io.homebeaver.uom;

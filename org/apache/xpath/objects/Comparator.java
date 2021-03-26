package org.apache.xpath.objects;

import org.apache.xml.utils.XMLString;

abstract class Comparator {
  abstract boolean compareStrings(XMLString paramXMLString1, XMLString paramXMLString2);
  
  abstract boolean compareNumbers(double paramDouble1, double paramDouble2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/objects/Comparator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
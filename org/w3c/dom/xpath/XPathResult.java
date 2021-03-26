package org.w3c.dom.xpath;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public interface XPathResult {
  public static final short ANY_TYPE = 0;
  
  public static final short NUMBER_TYPE = 1;
  
  public static final short STRING_TYPE = 2;
  
  public static final short BOOLEAN_TYPE = 3;
  
  public static final short UNORDERED_NODE_ITERATOR_TYPE = 4;
  
  public static final short ORDERED_NODE_ITERATOR_TYPE = 5;
  
  public static final short UNORDERED_NODE_SNAPSHOT_TYPE = 6;
  
  public static final short ORDERED_NODE_SNAPSHOT_TYPE = 7;
  
  public static final short ANY_UNORDERED_NODE_TYPE = 8;
  
  public static final short FIRST_ORDERED_NODE_TYPE = 9;
  
  short getResultType();
  
  double getNumberValue() throws XPathException;
  
  String getStringValue() throws XPathException;
  
  boolean getBooleanValue() throws XPathException;
  
  Node getSingleNodeValue() throws XPathException;
  
  boolean getInvalidIteratorState();
  
  int getSnapshotLength() throws XPathException;
  
  Node iterateNext() throws XPathException, DOMException;
  
  Node snapshotItem(int paramInt) throws XPathException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/xpath/XPathResult.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
package org.apache.xalan.xsltc;

import org.apache.xalan.xsltc.runtime.Hashtable;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.serializer.SerializationHandler;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public interface DOM {
  public static final int FIRST_TYPE = 0;
  
  public static final int NO_TYPE = -1;
  
  public static final int NULL = 0;
  
  public static final int RETURN_CURRENT = 0;
  
  public static final int RETURN_PARENT = 1;
  
  public static final int SIMPLE_RTF = 0;
  
  public static final int ADAPTIVE_RTF = 1;
  
  public static final int TREE_RTF = 2;
  
  DTMAxisIterator getIterator();
  
  String getStringValue();
  
  DTMAxisIterator getChildren(int paramInt);
  
  DTMAxisIterator getTypedChildren(int paramInt);
  
  DTMAxisIterator getAxisIterator(int paramInt);
  
  DTMAxisIterator getTypedAxisIterator(int paramInt1, int paramInt2);
  
  DTMAxisIterator getNthDescendant(int paramInt1, int paramInt2, boolean paramBoolean);
  
  DTMAxisIterator getNamespaceAxisIterator(int paramInt1, int paramInt2);
  
  DTMAxisIterator getNodeValueIterator(DTMAxisIterator paramDTMAxisIterator, int paramInt, String paramString, boolean paramBoolean);
  
  DTMAxisIterator orderNodes(DTMAxisIterator paramDTMAxisIterator, int paramInt);
  
  String getNodeName(int paramInt);
  
  String getNodeNameX(int paramInt);
  
  String getNamespaceName(int paramInt);
  
  int getExpandedTypeID(int paramInt);
  
  int getNamespaceType(int paramInt);
  
  int getParent(int paramInt);
  
  int getAttributeNode(int paramInt1, int paramInt2);
  
  String getStringValueX(int paramInt);
  
  void copy(int paramInt, SerializationHandler paramSerializationHandler) throws TransletException;
  
  void copy(DTMAxisIterator paramDTMAxisIterator, SerializationHandler paramSerializationHandler) throws TransletException;
  
  String shallowCopy(int paramInt, SerializationHandler paramSerializationHandler) throws TransletException;
  
  boolean lessThan(int paramInt1, int paramInt2);
  
  void characters(int paramInt, SerializationHandler paramSerializationHandler) throws TransletException;
  
  Node makeNode(int paramInt);
  
  Node makeNode(DTMAxisIterator paramDTMAxisIterator);
  
  NodeList makeNodeList(int paramInt);
  
  NodeList makeNodeList(DTMAxisIterator paramDTMAxisIterator);
  
  String getLanguage(int paramInt);
  
  int getSize();
  
  String getDocumentURI(int paramInt);
  
  void setFilter(StripFilter paramStripFilter);
  
  void setupMapping(String[] paramArrayOfString1, String[] paramArrayOfString2, int[] paramArrayOfint, String[] paramArrayOfString3);
  
  boolean isElement(int paramInt);
  
  boolean isAttribute(int paramInt);
  
  String lookupNamespace(int paramInt, String paramString) throws TransletException;
  
  int getNodeIdent(int paramInt);
  
  int getNodeHandle(int paramInt);
  
  DOM getResultTreeFrag(int paramInt1, int paramInt2);
  
  DOM getResultTreeFrag(int paramInt1, int paramInt2, boolean paramBoolean);
  
  SerializationHandler getOutputDomBuilder();
  
  int getNSType(int paramInt);
  
  int getDocument();
  
  String getUnparsedEntityURI(String paramString);
  
  Hashtable getElementsWithIDs();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/DOM.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
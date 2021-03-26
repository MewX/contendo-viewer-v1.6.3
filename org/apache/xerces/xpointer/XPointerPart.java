package org.apache.xerces.xpointer;

import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XNIException;

public interface XPointerPart {
  public static final int EVENT_ELEMENT_START = 0;
  
  public static final int EVENT_ELEMENT_END = 1;
  
  public static final int EVENT_ELEMENT_EMPTY = 2;
  
  void parseXPointer(String paramString) throws XNIException;
  
  boolean resolveXPointer(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations, int paramInt) throws XNIException;
  
  boolean isFragmentResolved() throws XNIException;
  
  boolean isChildFragmentResolved() throws XNIException;
  
  String getSchemeName();
  
  String getSchemeData();
  
  void setSchemeName(String paramString);
  
  void setSchemeData(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xpointer/XPointerPart.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
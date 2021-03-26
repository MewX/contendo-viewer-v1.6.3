package org.apache.batik.dom.svg;

import org.w3c.dom.Attr;

public interface LiveAttributeValue {
  void attrAdded(Attr paramAttr, String paramString);
  
  void attrModified(Attr paramAttr, String paramString1, String paramString2);
  
  void attrRemoved(Attr paramAttr, String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/LiveAttributeValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
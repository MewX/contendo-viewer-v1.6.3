package org.apache.xmlgraphics.xmp;

import java.util.Iterator;
import org.apache.xmlgraphics.util.QName;

public interface PropertyAccess {
  void setProperty(XMPProperty paramXMPProperty);
  
  XMPProperty getProperty(String paramString1, String paramString2);
  
  XMPProperty getProperty(QName paramQName);
  
  XMPProperty removeProperty(QName paramQName);
  
  XMPProperty getValueProperty();
  
  int getPropertyCount();
  
  Iterator iterator();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/PropertyAccess.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
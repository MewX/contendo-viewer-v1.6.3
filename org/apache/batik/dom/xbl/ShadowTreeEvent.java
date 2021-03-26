package org.apache.batik.dom.xbl;

import org.w3c.dom.events.Event;

public interface ShadowTreeEvent extends Event {
  XBLShadowTreeElement getXblShadowTree();
  
  void initShadowTreeEvent(String paramString, boolean paramBoolean1, boolean paramBoolean2, XBLShadowTreeElement paramXBLShadowTreeElement);
  
  void initShadowTreeEventNS(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, XBLShadowTreeElement paramXBLShadowTreeElement);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/xbl/ShadowTreeEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
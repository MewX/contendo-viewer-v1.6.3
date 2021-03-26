package org.w3c.dom.events;

import org.w3c.dom.Node;

public interface MutationEvent extends Event {
  public static final short MODIFICATION = 1;
  
  public static final short ADDITION = 2;
  
  public static final short REMOVAL = 3;
  
  Node getRelatedNode();
  
  String getPrevValue();
  
  String getNewValue();
  
  String getAttrName();
  
  short getAttrChange();
  
  void initMutationEvent(String paramString1, boolean paramBoolean1, boolean paramBoolean2, Node paramNode, String paramString2, String paramString3, String paramString4, short paramShort);
  
  void initMutationEventNS(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, Node paramNode, String paramString3, String paramString4, String paramString5, short paramShort);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/events/MutationEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
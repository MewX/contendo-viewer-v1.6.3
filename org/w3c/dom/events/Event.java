package org.w3c.dom.events;

public interface Event {
  public static final short CAPTURING_PHASE = 1;
  
  public static final short AT_TARGET = 2;
  
  public static final short BUBBLING_PHASE = 3;
  
  String getType();
  
  EventTarget getTarget();
  
  EventTarget getCurrentTarget();
  
  short getEventPhase();
  
  boolean getBubbles();
  
  boolean getCancelable();
  
  long getTimeStamp();
  
  void stopPropagation();
  
  void preventDefault();
  
  void initEvent(String paramString, boolean paramBoolean1, boolean paramBoolean2);
  
  String getNamespaceURI();
  
  void stopImmediatePropagation();
  
  boolean getDefaultPrevented();
  
  void initEventNS(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/events/Event.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
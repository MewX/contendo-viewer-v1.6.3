package org.apache.batik.w3c.dom.events;

import org.w3c.dom.events.UIEvent;
import org.w3c.dom.views.AbstractView;

public interface KeyboardEvent extends UIEvent {
  public static final int DOM_KEY_LOCATION_STANDARD = 0;
  
  public static final int DOM_KEY_LOCATION_LEFT = 1;
  
  public static final int DOM_KEY_LOCATION_RIGHT = 2;
  
  public static final int DOM_KEY_LOCATION_NUMPAD = 3;
  
  String getKeyIdentifier();
  
  int getKeyLocation();
  
  boolean getCtrlKey();
  
  boolean getShiftKey();
  
  boolean getAltKey();
  
  boolean getMetaKey();
  
  boolean getModifierState(String paramString);
  
  void initKeyboardEvent(String paramString1, boolean paramBoolean1, boolean paramBoolean2, AbstractView paramAbstractView, String paramString2, int paramInt, String paramString3);
  
  void initKeyboardEventNS(String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2, AbstractView paramAbstractView, String paramString3, int paramInt, String paramString4);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/w3c/dom/events/KeyboardEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
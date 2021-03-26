package org.w3c.dom.stylesheets;

import org.w3c.dom.Node;

public interface StyleSheet {
  String getType();
  
  boolean getDisabled();
  
  void setDisabled(boolean paramBoolean);
  
  Node getOwnerNode();
  
  StyleSheet getParentStyleSheet();
  
  String getHref();
  
  String getTitle();
  
  MediaList getMedia();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/stylesheets/StyleSheet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
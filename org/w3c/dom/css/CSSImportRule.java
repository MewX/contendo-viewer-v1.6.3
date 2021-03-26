package org.w3c.dom.css;

import org.w3c.dom.stylesheets.MediaList;

public interface CSSImportRule extends CSSRule {
  String getHref();
  
  MediaList getMedia();
  
  CSSStyleSheet getStyleSheet();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/css/CSSImportRule.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
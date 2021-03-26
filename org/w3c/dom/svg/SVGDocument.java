package org.w3c.dom.svg;

import org.w3c.dom.Document;
import org.w3c.dom.events.DocumentEvent;

public interface SVGDocument extends Document, DocumentEvent {
  String getTitle();
  
  String getReferrer();
  
  String getDomain();
  
  String getURL();
  
  SVGSVGElement getRootElement();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/svg/SVGDocument.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
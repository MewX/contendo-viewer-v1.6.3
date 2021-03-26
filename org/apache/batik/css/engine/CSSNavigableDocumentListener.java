package org.apache.batik.css.engine;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public interface CSSNavigableDocumentListener {
  void nodeInserted(Node paramNode);
  
  void nodeToBeRemoved(Node paramNode);
  
  void subtreeModified(Node paramNode);
  
  void characterDataModified(Node paramNode);
  
  void attrModified(Element paramElement, Attr paramAttr, short paramShort, String paramString1, String paramString2);
  
  void overrideStyleTextChanged(CSSStylableElement paramCSSStylableElement, String paramString);
  
  void overrideStylePropertyRemoved(CSSStylableElement paramCSSStylableElement, String paramString);
  
  void overrideStylePropertyChanged(CSSStylableElement paramCSSStylableElement, String paramString1, String paramString2, String paramString3);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/CSSNavigableDocumentListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
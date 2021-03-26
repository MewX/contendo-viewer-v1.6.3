package org.w3c.dom.html;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public interface HTMLDocument extends Document {
  String getTitle();
  
  void setTitle(String paramString);
  
  String getReferrer();
  
  String getDomain();
  
  String getURL();
  
  HTMLElement getBody();
  
  void setBody(HTMLElement paramHTMLElement);
  
  HTMLCollection getImages();
  
  HTMLCollection getApplets();
  
  HTMLCollection getLinks();
  
  HTMLCollection getForms();
  
  HTMLCollection getAnchors();
  
  String getCookie();
  
  void setCookie(String paramString);
  
  void open();
  
  void close();
  
  void write(String paramString);
  
  void writeln(String paramString);
  
  Element getElementById(String paramString);
  
  NodeList getElementsByName(String paramString);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/html/HTMLDocument.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
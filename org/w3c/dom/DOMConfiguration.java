package org.w3c.dom;

public interface DOMConfiguration {
  void setParameter(String paramString, Object paramObject) throws DOMException;
  
  Object getParameter(String paramString) throws DOMException;
  
  boolean canSetParameter(String paramString, Object paramObject);
  
  DOMStringList getParameterNames();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/dom/DOMConfiguration.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */
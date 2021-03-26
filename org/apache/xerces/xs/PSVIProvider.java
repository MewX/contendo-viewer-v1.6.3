package org.apache.xerces.xs;

public interface PSVIProvider {
  ElementPSVI getElementPSVI();
  
  AttributePSVI getAttributePSVI(int paramInt);
  
  AttributePSVI getAttributePSVIByName(String paramString1, String paramString2);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xs/PSVIProvider.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */
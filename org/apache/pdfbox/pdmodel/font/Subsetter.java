package org.apache.pdfbox.pdmodel.font;

import java.io.IOException;

interface Subsetter {
  void addToSubset(int paramInt);
  
  void subset() throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/Subsetter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
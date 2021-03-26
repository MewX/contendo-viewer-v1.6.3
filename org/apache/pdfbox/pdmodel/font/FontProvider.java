package org.apache.pdfbox.pdmodel.font;

import java.util.List;

public abstract class FontProvider {
  public abstract String toDebugString();
  
  public abstract List<? extends FontInfo> getFontInfo();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/FontProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
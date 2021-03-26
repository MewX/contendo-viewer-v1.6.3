package org.apache.pdfbox.pdmodel.font;

import java.awt.geom.GeneralPath;
import java.io.IOException;

public interface PDVectorFont {
  GeneralPath getPath(int paramInt) throws IOException;
  
  boolean hasGlyph(int paramInt) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDVectorFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
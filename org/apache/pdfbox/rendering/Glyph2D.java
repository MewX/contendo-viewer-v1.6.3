package org.apache.pdfbox.rendering;

import java.awt.geom.GeneralPath;
import java.io.IOException;

interface Glyph2D {
  GeneralPath getPathForCharacterCode(int paramInt) throws IOException;
  
  void dispose();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/rendering/Glyph2D.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
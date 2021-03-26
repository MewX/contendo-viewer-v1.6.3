package org.apache.fontbox;

import java.awt.geom.GeneralPath;
import java.io.IOException;
import java.util.List;
import org.apache.fontbox.util.BoundingBox;

public interface FontBoxFont {
  String getName() throws IOException;
  
  BoundingBox getFontBBox() throws IOException;
  
  List<Number> getFontMatrix() throws IOException;
  
  GeneralPath getPath(String paramString) throws IOException;
  
  float getWidth(String paramString) throws IOException;
  
  boolean hasGlyph(String paramString) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/FontBoxFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
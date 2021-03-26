package org.apache.fontbox.ttf;

import java.util.List;

public interface CmapLookup {
  int getGlyphId(int paramInt);
  
  List<Integer> getCharCodes(int paramInt);
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/CmapLookup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
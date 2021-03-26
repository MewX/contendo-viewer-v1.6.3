package org.apache.fontbox.ttf;

public interface GlyphDescription {
  int getEndPtOfContours(int paramInt);
  
  byte getFlags(int paramInt);
  
  short getXCoordinate(int paramInt);
  
  short getYCoordinate(int paramInt);
  
  boolean isComposite();
  
  int getPointCount();
  
  int getContourCount();
  
  void resolve();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/GlyphDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
package org.apache.batik.svggen.font.table;

public interface GlyphDescription {
  int getEndPtOfContours(int paramInt);
  
  byte getFlags(int paramInt);
  
  short getXCoordinate(int paramInt);
  
  short getYCoordinate(int paramInt);
  
  short getXMaximum();
  
  short getXMinimum();
  
  short getYMaximum();
  
  short getYMinimum();
  
  boolean isComposite();
  
  int getPointCount();
  
  int getContourCount();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/table/GlyphDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
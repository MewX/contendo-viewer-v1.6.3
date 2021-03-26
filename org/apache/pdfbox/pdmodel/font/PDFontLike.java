package org.apache.pdfbox.pdmodel.font;

import java.io.IOException;
import org.apache.fontbox.util.BoundingBox;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.Vector;

public interface PDFontLike {
  String getName();
  
  PDFontDescriptor getFontDescriptor();
  
  Matrix getFontMatrix();
  
  BoundingBox getBoundingBox() throws IOException;
  
  Vector getPositionVector(int paramInt);
  
  @Deprecated
  float getHeight(int paramInt) throws IOException;
  
  float getWidth(int paramInt) throws IOException;
  
  boolean hasExplicitWidth(int paramInt) throws IOException;
  
  float getWidthFromFont(int paramInt) throws IOException;
  
  boolean isEmbedded();
  
  boolean isDamaged();
  
  float getAverageFontWidth();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDFontLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
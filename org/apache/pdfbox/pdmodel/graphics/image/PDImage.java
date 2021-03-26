package org.apache.pdfbox.pdmodel.graphics.image;

import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.filter.DecodeOptions;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;

public interface PDImage extends COSObjectable {
  BufferedImage getImage() throws IOException;
  
  BufferedImage getImage(Rectangle paramRectangle, int paramInt) throws IOException;
  
  BufferedImage getStencilImage(Paint paramPaint) throws IOException;
  
  InputStream createInputStream() throws IOException;
  
  InputStream createInputStream(List<String> paramList) throws IOException;
  
  InputStream createInputStream(DecodeOptions paramDecodeOptions) throws IOException;
  
  boolean isEmpty();
  
  boolean isStencil();
  
  void setStencil(boolean paramBoolean);
  
  int getBitsPerComponent();
  
  void setBitsPerComponent(int paramInt);
  
  PDColorSpace getColorSpace() throws IOException;
  
  void setColorSpace(PDColorSpace paramPDColorSpace);
  
  int getHeight();
  
  void setHeight(int paramInt);
  
  int getWidth();
  
  void setWidth(int paramInt);
  
  void setDecode(COSArray paramCOSArray);
  
  COSArray getDecode();
  
  boolean getInterpolate();
  
  void setInterpolate(boolean paramBoolean);
  
  String getSuffix();
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/image/PDImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
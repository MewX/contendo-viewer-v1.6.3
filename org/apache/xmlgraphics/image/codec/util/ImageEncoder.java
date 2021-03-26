package org.apache.xmlgraphics.image.codec.util;

import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;

public interface ImageEncoder {
  ImageEncodeParam getParam();
  
  void setParam(ImageEncodeParam paramImageEncodeParam);
  
  OutputStream getOutputStream();
  
  void encode(Raster paramRaster, ColorModel paramColorModel) throws IOException;
  
  void encode(RenderedImage paramRenderedImage) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/util/ImageEncoder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */
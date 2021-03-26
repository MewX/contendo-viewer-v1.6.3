package org.apache.batik.ext.awt.image.codec.util;

import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.io.IOException;

public interface ImageDecoder {
  ImageDecodeParam getParam();
  
  void setParam(ImageDecodeParam paramImageDecodeParam);
  
  SeekableStream getInputStream();
  
  int getNumPages() throws IOException;
  
  Raster decodeAsRaster() throws IOException;
  
  Raster decodeAsRaster(int paramInt) throws IOException;
  
  RenderedImage decodeAsRenderedImage() throws IOException;
  
  RenderedImage decodeAsRenderedImage(int paramInt) throws IOException;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/util/ImageDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */